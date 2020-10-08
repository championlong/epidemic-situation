<template>
  <div class="only">
    <message :title="title" :sums="sums" :updateTime="updateTime"></message>
    <china id="chart" style="width: 100% ;height: 800px;margin-top: 40px" align="center"></china>
  </div>
</template>

<script>

  import message from '@/components/message'
  import map from '@/components/map_china'

  export default {
    name: 'Index',
    created() {
    },
    props: {},
    data() {
      return {
        title: "国内疫情",
        sums: [
          {name: 'suspected', text: '现有确诊', color: "#FF0000", sum: 376, add: "-2"},
          {name: 'suspected', text: '无症状感染者', color: "#FF961E", sum: 391, add: "+26"},
          {name: 'suspected', text: '境外输入', color: "#FFCC00", sum: 2802, add: "+14"},
          {name: 'confirmed', text: '累计确诊', color: "#F55253", sum: 90966, add: "+15"},
          {name: 'ok', text: '累计治愈', color: "#178B50", sum: 85844, add: "+17"},
          {name: 'die', text: '累计死亡', color: "#66666c", sum: 4746, add: "+0"}
        ],
        updateTime:"2020-09-27 09:37",
      }
    },
    mounted() {

    },
    methods: {
    },
    components: {
      'message': message,
      'china':map,
    }
  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .only {
    width: 100%;
    margin: 0px;
    margin-top: 60px;
    /* transform:translateY(60px); */
  }

</style>

