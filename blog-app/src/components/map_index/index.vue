<template>
  <el-row class="warp">
    <el-col :span="24" class="warp-main">
      <div>
        <div id="allmap"></div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "demo",
    data() {
      return {}
    },
    mounted() {
      /**================================================= 地图初始化 start ============================================*/
      var map = new BMap.Map("allmap");    // 创建Map实例
      var data_info = [[126.615685, 45.72757, 2000, 80],
        [126.617122, 45.716924, 4000, 100],];
      var points = [
        {"lng": 126.619601, "lat": 45.720693, "count": 50},
        {"lng": 126.619601, "lat": 45.720693, "count": 50},
        {"lng": 126.619601, "lat": 45.720693, "count": 15},
        {"lng": 126.619601, "lat": 45.720693, "count": 40},
        {"lng": 126.615685, "lat": 45.72757, "count": 20},
        {"lng": 126.619638, "lat": 45.720669, "count": 6},
        {"lng": 126.619633, "lat": 45.720685, "count": 18},
        {"lng": 126.617122, "lat": 45.716924, "count": 20},
        {"lng": 126.619620, "lat": 45.720693, "count": 11},
        {"lng": 126.619601, "lat": 45.720693, "count": 70},
        {"lng": 126.619601, "lat": 45.720693, "count": 42},
        {"lng": 126.619601, "lat": 45.720693, "count": 4},
        {"lng": 126.619601, "lat": 45.720693, "count": 27},
        {"lng": 126.619601, "lat": 45.720693, "count": 23},
        {"lng": 126.619601, "lat": 45.720693, "count": 60},
        {"lng": 126.619601, "lat": 45.720693, "count": 8},
        {"lng": 126.619601, "lat": 45.720693, "count": 15},
        {"lng": 126.619601, "lat": 45.720693, "count": 25},
        {"lng": 126.616367, "lat": 45.720693, "count": 21},
        {"lng": 126.616367, "lat": 45.720693, "count": 1},
        {"lng": 126.616367, "lat": 45.720693, "count": 51},
        {"lng": 126.616367, "lat": 45.720693, "count": 7},
        {"lng": 126.616367, "lat": 45.720693, "count": 11},
        {"lng": 126.616367, "lat": 45.720693, "count": 35},
        {"lng": 126.616367, "lat": 45.720693, "count": 22},
        {"lng": 126.616367, "lat": 45.720691, "count": 4},
        {"lng": 126.616367, "lat": 45.720698, "count": 5},
        {"lng": 126.616367, "lat": 45.720698, "count": 3},
        {"lng": 126.616367, "lat": 45.720695, "count": 100},
        {"lng": 126.616367, "lat": 45.720693, "count": 87},
        {"lng": 126.616367, "lat": 45.720693, "count": 32},];

      var opts = {
        width: 300,     // 信息窗口宽度
        height: 150,     // 信息窗口高度
        enableMessage: true//设置允许信息窗发送短息
      };

      for (var i = 0; i < data_info.length; i++) {
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0], data_info[i][1]));  // 创建标注
        var total = data_info[i][2];
        var present = data_info[i][3];

        var html="<div> " +
          "<h5 class='modal-title' id='exampleModalLabel'>容量信息</h5>"+
          "<hr class=\"my-1\">" +
          "<p>允许容量为："+ total +"</p>" +
          "<p>当前容量为："+ present +"</p>" +
          "<span class=\"badge badge-pill badge-primary\">确认安全</span>\n" +
          "</div>";
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
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(html, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
      }

      map.centerAndZoom(new BMap.Point(126.622871, 45.721354), 15);  // 初始化地图,设置中心点坐标和地图级别
      //添加地图类型控件
      map.addControl(new BMap.MapTypeControl({
        mapTypes: [
          BMAP_NORMAL_MAP,
          BMAP_HYBRID_MAP
        ]
      }));
      map.setCurrentCity("哈尔滨");          // 设置地图显示的城市 此项是必须设置的
      map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
      var heatmapOverlay = new BMapLib.HeatmapOverlay({"radius": 20});
      map.addOverlay(heatmapOverlay);
      heatmapOverlay.setDataSet({data: points, max: 30});

      // 定义一个控件类,即function
      function ZoomControl() {
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
        this.defaultOffset = new BMap.Size(10, 10);
      }

      // 通过JavaScript的prototype属性继承于BMap.Control
      ZoomControl.prototype = new BMap.Control();
      // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
      // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
      ZoomControl.prototype.initialize = function (map) {
        var redio = {b: false}
        // 创建一个DOM元素
        var div = document.createElement("div");
        // 添加文字说明
        div.appendChild(document.createTextNode("热力图"));
        // 设置样式
        div.style.cursor = "pointer";
        div.style.backgroundColor = "rgb(255, 255, 255)";
        div.style.boxShadow = "rgba(0, 0, 0, 0.35) 2px 2px 3px"
        div.style.borderWidth = "1px"
        div.style.borderStyle = "solid"
        div.style.borderColor = "rgb(139, 164, 220)"
        div.style.padding = "2px 6px"
        div.style.fontStyle = "normal"
        div.style.fontVariant = "normal"
        div.style.fontStretch = "normal"
        div.style.fontSize = "12px"
        div.style.lineHeight = "1.3em"
        div.style.fontFamily = "arial"
        div.style.sansSerif = "text-align: center"
        div.style.whiteSpace = "nowrap"
        div.style.borderRadius = "0px 3px 3px 0px"
        div.style.color = "rgb(0, 0, 0)"
        // 绑定事件
        div.onclick = function () {
          Heatmap(redio)
        }
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
      }
      // 创建控件
      var myZoomCtrl = new ZoomControl();
      // 添加到地图当中
      map.addControl(myZoomCtrl);

      function Heatmap(redio) {
        if (redio.b === true) {
          heatmapOverlay.show();
          redio.b = false
        } else {
          heatmapOverlay.hide();
          redio.b = true
        }
      }
    },
    methods: {}
  }
</script>

<style scoped>
  #allmap {
    height: 50vh;
  }
</style>
