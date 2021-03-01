<template>
  <div id="map">
    <div></div>
  </div>
</template>

<script>
import {getChinaList} from '@/api/epidemic'
import echarts from "echarts";
import "echarts/map/js/china";
import "echarts/map/js/province/beijing";

export default {
  name: "china",
  data() {
    return {
      chinaList:[],
      options: {
        title:{
          text: '中国疫情地图',
          left: 'center',
          subtext:'点击标题返回全国地图',
          triggerEvent:true,
          textStyle: {
            fontWeight: 'normal',
          }
        },
        series: [
          {
            type: "map",
            map: "china",
            label: {
              show: true,
              color: "black",
              fontSize: 10,
            },
            zoom: 1.25,
            itemStyle: {
              borderColor: "gray",
            },
            emphasis: {
              //区域高亮显示时状态
              label: {
                //高亮时字体样式
              },
              itemStyle: {
                //高亮是区域样式
                borderColor: "#ccc",
              },
            },
          },
        ],
        tooltip: {
          //提示框组件
          show: true,
          trigger: "item",
          formatter:" 地区：{b}<br/>现有确诊：{c}", // {a}（系列名称），{b}（区域名称），{c}（合并数值）, {d}（无）
        },
        visualMap: {
          //视觉映射组件
          type: "piecewise", //映射组件类型(1、piecewise--分段型;2、continuous--连续型)
          inRange: {
            color: ["#CCC", "#F2B252", "#D1241A"], //数据段范围颜色
          },
          pieces: [
            //地图左下角显示可视化
            { min: 10000, color: "#D1241A" }, // 不指定 max，表示 max 为无限大（Infinity）。
            { min: 1000, max: 9999, color: "#F27152" },
            { min: 100, max: 999, color: "#F28E52" },
            { min: 10, max: 99, color: "#F2B252" },
            { min: 1, max: 9, color: "#FFFFCC" },
            { value: 0 }, // 表示 value 等于 0 的情况。
          ],
        },
      },
    };
  },

  mounted() {
    this.mychinaChart = echarts.init(document.getElementById("chart"));
    var cityArr = [
      [
        "上海",
        "河北",
        "山西",
        "内蒙古",
        "辽宁",
        "吉林",
        "黑龙江",
        "江苏",
        "浙江",
        "安徽",
        "福建",
        "江西",
        "山东",
        "河南",
        "湖北",
        "湖南",
        "广东",
        "广西",
        "海南",
        "四川",
        "贵州",
        "云南",
        "西藏",
        "陕西",
        "甘肃",
        "青海",
        "宁夏",
        "新疆",
        "北京",
        "天津",
        "重庆",
        "香港",
        "澳门",
        "台湾",
      ],
      [
        "shanghai",
        "hebei",
        "shanxi",
        "neimenggu",
        "liaoning",
        "jilin",
        "heilongjiang",
        "jiangsu",
        "zhejiang",
        "anhui",
        "fujian",
        "jiangxi",
        "shandong",
        "henan",
        "hubei",
        "hunan",
        "guangdong",
        "guangxi",
        "hainan",
        "sichuan",
        "guizhou",
        "yunnan",
        "xizang",
        "shanxi1",
        "gansu",
        "qinghai",
        "ningxia",
        "xinjiang",
        "beijing",
        "tianjin",
        "chongqing",
        "xianggang",
        "aomen",
        "taiwan",
      ],
    ];
    getChinaList().then(data => {
      this.options.series[0].data = data.data
      this.mychinaChart.setOption(this.options, true);
    });
    this.mychinaChart.setOption(this.options, true);

    this.mychinaChart.on("click", async (params) => {
      var cityName = params.name;
      for (var i = 0, len = cityArr[0].length; i < len; i++) {
        if (cityName == cityArr[0][i]) {
          // 获取得城市拼音
          await import(`echarts/map/js/province/${cityArr[1][i]}`);
          this.options.series[0].map = cityArr[0][i];
          this.options.series[0].label.show = false;
          this.options.title.text = params.name + '疫情地图';
          this.mychinaChart.setOption(this.options, true);
        }
      }
    if (params.componentType == 'title') {
      this.options.series[0].map = "china";
      this.options.series[0].label.show = true;
      this.options.title.text = '中国疫情地图';
      this.mychinaChart.setOption(this.options, true);
    }
    });
  },

  // watch: {
  //   //观察data的变化
  //   options: {
  //     handler: function (val, oldVal) {
  //       console.log("hahhaha");
  //     },
  //     deep: true, // 深度监听，监听到更深层级值的变化
  //   },
  // },
  methods: {},
};
</script>
<style scoped>

</style>
