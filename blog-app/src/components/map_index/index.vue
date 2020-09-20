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
      var data_info = [[126.615685, 45.72757, 2000, 8000, "凯德广场"],
        [126.617122, 45.716924, 4000, 100, "962医212院212"],];
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
        width: 150,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        // title: "信息状态",
      };

      for (var i = 0; i < data_info.length; i++) {
        var marker = new BMapGL.Marker(new BMapGL.Point(data_info[i][0], data_info[i][1]));  // 创建标注
        var total = data_info[i][2];
        var present = data_info[i][3];
        var place = data_info[i][4]
        if(total >= present){
          var html="<div> " +
            "<p>" +
            "<span class='badge badge-success'>"+place+"</span>" +
            "疫情期间可容纳" +
            "<span class='badge badge-success'>"+total+"</span>" +
            "人，扫码人数已有"+
            "<span class='badge badge-success'>"+present+"</span>" +
            "人。" +
            "<span class=\"badge badge-primary\" style='float: right'>确认安全</span>" +
            "</p>\n" +
            "</div>";
        }else{
        
          var html="<div style='width: 200px;'> " +
            "<p>" +
            "<span class='badge badge-success'>"+place+"</span>" +
            " 疫情期间可容纳 " +
            "<span class='badge badge-success'>"+total+"</span>" +
            " 有 "+
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
      map.addControl(new BMapGL.MapTypeControl({
        mapTypes: [
          BMAP_HYBRID_MAP
        ]
      }));
      map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

      // var heatmapOverlay = new BMapLib.HeatmapOverlay({"radius": 20});
      // map.addOverlay(heatmapOverlay);
      // heatmapOverlay.setDataSet({data: points, max: 30});
      //
      // // 定义一个控件类,即function
      // function ZoomControl() {
      //   // 默认停靠位置和偏移量
      //   this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
      //   this.defaultOffset = new BMapGL.Size(10, 10);
      // }

      // // 通过JavaScript的prototype属性继承于BMap.Control
      // ZoomControl.prototype = new BMapGL.Control();
      // // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
      // // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
      // ZoomControl.prototype.initialize = function (map) {
      //   var redio = {b: false}
      //   // 创建一个DOM元素
      //   var div = document.createElement("div");
      //   // 添加文字说明
      //   div.appendChild(document.createTextNode("热力图"));
      //   // 设置样式
      //   div.style.cursor = "pointer";
      //   div.style.backgroundColor = "rgb(255, 255, 255)";
      //   div.style.boxShadow = "rgba(0, 0, 0, 0.35) 2px 2px 3px"
      //   div.style.borderWidth = "1px"
      //   div.style.borderStyle = "solid"
      //   div.style.borderColor = "rgb(139, 164, 220)"
      //   div.style.padding = "2px 6px"
      //   div.style.fontStyle = "normal"
      //   div.style.fontVariant = "normal"
      //   div.style.fontStretch = "normal"
      //   div.style.fontSize = "12px"
      //   div.style.lineHeight = "1.3em"
      //   div.style.fontFamily = "arial"
      //   div.style.sansSerif = "text-align: center"
      //   div.style.whiteSpace = "nowrap"
      //   div.style.borderRadius = "0px 3px 3px 0px"
      //   div.style.color = "rgb(0, 0, 0)"
      //   // 绑定事件
      //   div.onclick = function () {
      //     Heatmap(redio)
      //   }
      //   // 添加DOM元素到地图中
      //   map.getContainer().appendChild(div);
      //   // 将DOM元素返回
      //   return div;
      // }
      // // 创建控件
      // var myZoomCtrl = new ZoomControl();
      // // 添加到地图当中
      // map.addControl(myZoomCtrl);
      //
      // function Heatmap(redio) {
      //   if (redio.b === true) {
      //     heatmapOverlay.show();
      //     redio.b = false
      //   } else {
      //     heatmapOverlay.hide();
      //     redio.b = true
      //   }
      // }
    },
    methods: {
      initMapHeight() {
        var main = document.getElementById("map");
        main.style.height = window.innerHeight-60 + "px";
      },
    }
  }
</script>

<style scoped>
  #allmap {
    height: 100%;
    /* overflow: hidden; */
    position: relative;
    
  }
    #map {
    /* overflow: hidden; */
    position: relative;
  }
</style>
