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
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    mounted () {
      /**================================================= 地图初始化 start ============================================*/
      var map = new BMap.Map("allmap");    // 创建Map实例
      var data_info = [[126.615685,45.72757,"当前商城容量：2000"],
        [126.617122,45.716924,"当前医院人数：5000"],
      ];
      var opts = {
        width : 250,     // 信息窗口宽度
        height: 80,     // 信息窗口高度
        title : "信息窗口" , // 信息窗口标题
        enableMessage:true//设置允许信息窗发送短息
      };
      for(var i=0;i<data_info.length;i++){
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
        var content = data_info[i][2];
        map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(content,marker);
      }

      function addClickHandler(content,marker){
        marker.addEventListener("click",function(e){
          openInfo(content,e)}
        );
      }
      function openInfo(content,e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
      }
      map.centerAndZoom(new BMap.Point(126.622871,45.721354), 15);  // 初始化地图,设置中心点坐标和地图级别
      //添加地图类型控件
      map.addControl(new BMap.MapTypeControl({
        mapTypes:[
          BMAP_NORMAL_MAP,
          BMAP_HYBRID_MAP
        ]}));
      map.setCurrentCity("哈尔滨");          // 设置地图显示的城市 此项是必须设置的
      map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    }
  }
</script>

<style scoped>
  #allmap {
    height: 50vh;
  }
</style>
