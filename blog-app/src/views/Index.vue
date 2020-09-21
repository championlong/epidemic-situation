<template>
  <div class="only">
    <message :title="title" :sums="sums"></message>
    <div id="chart"  style="width: 100% ;height: 600px;margin-top: 50px" align="center"></div>
  </div>
</template>

<script>

  import message from '@/components/message'
import echarts from "echarts"
  import jsonp from "jsonp"
  import "echarts/map/js/china"
  export default {
    name: 'Index',
    created() {
    },
    props: {

    },
    data() {
      return {
        title: "国内疫情",
        sums: [
          {name: 'confirmed', text: '确诊', color: "#F55253", sum: 63951, add: "+19"},
          {name: 'suspected', text: '疑似', color: "#FF961E", sum: 10109 , add: "+2450"},
          {name: 'die', text: '死亡', color: "#66666c", sum: 1382 , add: "+1"},
          {name: 'ok', text: '治愈', color: "#178B50", sum: 7094, add: "+366"}
        ],
        mychinaChart:"",
      }
    },
    mounted () {
      let options = {
        series:[
          {
            type:"map",
            map:"china",
            data:[
              {name:'北京',value:'320'},
              {name:'天津',value:'362'},
              {name:'上海',value:'532'},
              {name:'重庆',value:'632'},
              {name:'湖北',value:'5990'}],
            label:{
              show:true,
              color:"black",
              fontSize:10
            },
            zoom:1.25,
            itemStyle:{
              borderColor:"gray"
            },
            emphasis:{//区域高亮显示时状态
              label:{//高亮时字体样式
              },
              itemStyle:{//高亮是区域样式
                borderColor:'#ccc'
              }
            }
          }
        ],
        tooltip:{//提示框组件
          show:true,
          trigger:'item',
          formatter: '地区：{b}<br/>确诊：{c}'// {a}（系列名称），{b}（区域名称），{c}（合并数值）, {d}（无）
        },
        visualMap:{//视觉映射组件
          type:'piecewise',//映射组件类型(1、piecewise--分段型;2、continuous--连续型)
          inRange:{
            color: ['#CCC', '#F2B252', '#D1241A']//数据段范围颜色
          },
          pieces: [//地图左下角显示可视化
            {min: 10000,color:'#D1241A'}, // 不指定 max，表示 max 为无限大（Infinity）。
            {min: 1000, max: 9999,color:'#F27152'},
            {min: 100, max: 999,color:'#F28E52'},
            {min: 10, max: 99,color:'#F2B252'},
            {min: 1, max: 9,color:'#A5A5A5'},
            {value: 0}, // 表示 value 等于 0 的情况。
          ]
        },
      };
      this.mychinaChart = echarts.init(document.getElementById("chart"));
      this.mychinaChart.setOption(options);
    },
    methods: {
    },
    components: {
      'message':message,
    }
  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .only {
    width: 100%;
    margin:0px;
    margin-top:60px;
    /* transform:translateY(60px); */
  }

</style>

