<template>
  <div>
    <div id="container" style="width:100%; height:99.9%;resize:both;"></div>
  </div>
</template>

<script>
  export default {
    name: "demo",
    data() {
      return {
        socket: null,
        data_info: [{"lat":45.715629,"lng":126.614659,"place":"哈尔滨理工大学","present":20000,"total":0},{"lat":45.711475,"lng":126.610687,"place":"广来菜馆","present":0,"total":0}]
      }
    },
    // watch: {
    //   data_info: {
    //     handler:function () {
    //       this.showInfo()
    //     }
    //   },
    // },
    created() {
      this.initWebSocket();
    },
    destroyed() {
      this.socket.close() //离开路由之后断开websocket连接
    },
    mounted() {
      this.initMapHeight();
      var map = new AMap.Map('container', {
        resizeEnable: true,
        rotateEnable: true,
        pitchEnable: true,
        zoom: 17,
        rotation: -15,
        viewMode: '3D',//开启3D视图,默认为关闭
        buildingAnimation: true,//楼块出现是否带动画
        expandZoomRange: true,
        zooms: [3, 20],
        center: [126.61977, 45.718752]
      });
      AMap.plugin([
        'AMap.ControlBar',
      ], function () {
        map.addControl(new AMap.ControlBar());
      });

      AMap.plugin('AMap.Geolocation', function () {
        var geolocation = new AMap.Geolocation({
          enableHighAccuracy: true,//是否使用高精度定位，默认:true
          buttonPosition: 'RB',    //定位按钮的停靠位置
          buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
          zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点
          showCircle: false,
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition(function (status, result) {
          if (status == 'complete') {
            onComplete(result)
          } else {
            onError(result)
          }
        });
      });

      function onComplete(data) {
        var circle = new AMap.Circle({
          center: [data.position.lng, data.position.lat],
          radius: 1000, //半径
          borderWeight: 3,
          strokeColor: "#FF33FF",
          strokeOpacity: 1,
          strokeWeight: 6,
          strokeOpacity: 0.2,
          fillOpacity: 0.4,
          strokeDasharray: [10, 10],
          // 线样式还支持 'dashed'
          fillColor: '#1791fc',
          zIndex: 50,
        })
        circle.setMap(map)
        // 缩放地图到合适的视野级别
        map.setFitView([circle])
      }

      //解析定位错误信息
      function onError(data) {
        alert('定位失败');
      }

      var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
      var htmlAndLnglat = this.showInfo()
      console.log(htmlAndLnglat+'222')
      var marker = new AMap.Marker({
        position: htmlAndLnglat.lnglat,
        map: map
      });
      marker.content = htmlAndLnglat.html;
      marker.on('click', markerClick);
      function markerClick(e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
      }

      map.setFitView();
    },
    methods: {
      initMapHeight() {
        var main = document.getElementById("container");
        main.style.height = window.innerHeight - 105 + "px";
      },
      showInfo(){
        console.log(this.data_info+'123')

        for (var i = 0; i < this.data_info.length; i++) {
            var lnglat = [this.data_info[i].lng,this.data_info[i].lat];
            var total = this.data_info[i].total;
            var present = this.data_info[i].present;
            var place = this.data_info[i].place
            if (total >= present) {
              var html = "<div style='width: 200px;'> " +
                "<p>" +
                "<span class='badge badge-success'>" + place + "</span>" +
                " 疫情期间可容纳 " +
                "<span class='badge badge-success'>" + total + "</span>" +
                " 人，目前有 " +
                "<span class='badge badge-success'>" + present + "</span>" +
                " 人。" +
                "<span class=\"badge badge-primary\" style='float:right;" + "margin-top: 5px;'>无需排队</span>" +
                "</p>\n" +
                "</div>";
            } else {
              var html = "<div style='width: 200px;'> " +
                "<p>" +
                "<span class='badge badge-success'>" + place + "</span>" +
                " 疫情期间可容纳 " +
                "<span class='badge badge-success'>" + total + "</span>" +
                " 人，目前有 " +
                "<span class='badge badge-danger'>" + present + "</span>" +
                " 人。" +
                "<span class=\"badge badge-danger\" style='float:right;" + "margin-top: 5px;'>需要排队</span>" +
                "</p>\n" +
                "</div>";
            }
        }
        return {
          lnglat: lnglat,
          html: html
        }
      },
      initWebSocket() {
        if (window.WebSocket) { //初始化weosocket
          this.socket = new WebSocket("ws://127.0.0.1:8088/ws");
          this.socket.onmessage = this.websocketonmessage;
          this.socket.onopen = this.websocketonopen;
          this.socket.onerror = this.websocketonerror;
          this.socket.onclose = this.websocketclose;
        } else {
          console.log("浏览器不支持websocket")
        }
      },
      websocketonopen() { //连接建立之后执行send方法发送数据
        console.log("建立成功")
        let actions = "test";
        this.websocketsend(actions);
      },
      websocketonerror() {//连接建立失败重连
        console.log("建立连接异常")
        this.initWebSocket();
      },
      websocketonmessage(e) { //数据接收
        // this.data_info = e.data;
        console.log(this.data_info)
      },
      websocketsend(Data) {//数据发送
        console.log("数据发送")
        this.socket.send(Data);
      },
      websocketclose(e) {  //关闭
        console.log('断开连接');
      },
    }
  }
</script>
<style scoped>
</style>
