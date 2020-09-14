<template>
  <div class="demo"></div>
</template>

<script>
  // import BMap from 'BMap'

  export default {
    name: 'Map',

    data() {
      return {

      }
    },
    mounted () {
      this.baiduMap()
    },
    methods: {
      baiduMap () {
        var map = new BMap.Map('allmap')
        var point = new BMap.Point(111.742579, 40.818675)
        map.centerAndZoom(point, 12)
        var marker = new BMap.Marker(point) // 创建标注
        map.addOverlay(marker) // 将标注添加到地图中
      }
    }
  }
</script>

<style>
  .demo {
    width: 200px;
    height: 200px;
    background-color: black;
  }
</style>
