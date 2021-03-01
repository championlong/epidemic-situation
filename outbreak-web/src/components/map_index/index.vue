<template>
  <div id="map" :style="{height:mapHeight}">
    <div id="allmap" ref="allmap">
    </div>
  </div>
</template>

<script>
  export default {
    name: "demo",
    data() {
      return {
        mapHeight: 100 + "px",
      }
    },
    mounted() {
      this.initMapHeight();
      window.onresize = () => {
        return (() => {
          this.mapHeight = window.innerHeight + 'px';
        })();
      };
      /**================================================= 地图初始化 start ============================================*/
      var map = new BMapGL.Map("allmap");    // 创建Map实例
      var data_info = [[126.615685, 45.72757, 2000, 2100, "凯德广场"],
        [126.617122, 45.716924, 4000, 100, "962医院"],
        [126.617843,45.720004, 400, 236, "千口顺饺子馆"],
        [126.618081,45.719381, 4000, 100, "蒙克巴雅尔烤羊腿"],
        [126.616828,45.720979, 500, 300, "学府水果超市"],
        [126.61977,45.718752, 4000, 100, "希诺扒王"],
        [126.61611,45.717795, 4000, 100, "王师傅盒饭快餐"],
        [126.61611,45.717795, 4000, 100, "玉凤食杂店"],
        [126.612395,45.716914, 4000, 100, "学新仓买"],
        [126.611254,45.720419, 4000, 100, "手擀刀削面"],
      ];
      var opts = {
        width: 150,     // 信息窗口宽度
        height: 80,     // 信息窗口高度
        title: "信息状态",// title: "信息状态",
      };
      var point = new BMap.Point(126.622871, 45.721354);
      var circle = new BMapGL.Circle(point,1000,{fillColor:"blue", strokeWeight:1,fillOpacity: 0.3 ,strokeOpacity:0.3}); //创建圆
      for (var i = 0; i < data_info.length; i++) {
        var marker = new BMapGL.Marker(new BMapGL.Point(data_info[i][0], data_info[i][1]));  // 创建标注
        var total = data_info[i][2];
        var present = data_info[i][3];
        var place = data_info[i][4]
        if(total >= present){
          var html="<div style='width: 200px;'> " +
            "<p>" +
            "<span class='badge badge-success'>"+place+"</span>" +
            " 疫情期间可容纳 " +
            "<span class='badge badge-success'>"+total+"</span>" +
            " 人，目前有 "+
            "<span class='badge badge-success'>"+present+"</span>" +
            " 人。" +
            "<span class=\"badge badge-primary\" style='float:right;"+"margin-top: 5px;'>无需排队</span>" +
            "</p>\n" +
            "</div>";
        }else{

          var html="<div style='width: 200px;'> " +
            "<p>" +
            "<span class='badge badge-success'>"+place+"</span>" +
            " 疫情期间可容纳 " +
            "<span class='badge badge-success'>"+total+"</span>" +
            " 人，目前有 "+
            "<span class='badge badge-danger'>"+present+"</span>" +
            " 人。" +
            "<span class=\"badge badge-danger\" style='float:right;"+"margin-top: 5px;'>需要排队</span>" +
            "</p>\n" +
            "</div>";
        }
        map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(html, marker);
      }
      function addClickHandler(html, marker) {
        marker.addEventListener("click", function (e) {
            openInfo(html, e)
          }
        );
      }
      function openInfo(html, e) {
        var p = e.target;
        var point = new BMapGL.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMapGL.InfoWindow(html, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
      }
      map.centerAndZoom(new BMapGL.Point(126.622871, 45.721354), 15);  // 初始化地图,设置中心点坐标和地图级别
      //添加地图类型控件
      map.addOverlay(circle);
      map.addControl(new BMapGL.MapTypeControl({
        mapTypes: [
          BMAP_HYBRID_MAP
        ]
      }));

      map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
      var navi3DCtrl = new BMapGL.NavigationControl3D();  // 添加3D控件
      map.addControl(navi3DCtrl);

    },
    methods: {
      initMapHeight() {
        var main = document.getElementById("map");
        main.style.height = window.innerHeight-105 + "px";
      },
    }
  }
</script>

<style scoped>
  #allmap {
    height: 100%;
    position: relative;
  }
  #map {
    position: relative;
  }
</style>
