<template>
  <div class="only">
    <message :title="title" :sums="sums"></message>
    <china id="chart" style="width: 100% ;height: 600px;margin-top: 50px" align="center"  @ready="onReady" @click="onClick"></china>
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
          {name: 'confirmed', text: '确诊', color: "#F55253", sum: 63951, add: "+19"},
          {name: 'suspected', text: '疑似', color: "#FF961E", sum: 10109, add: "+2450"},
          {name: 'die', text: '死亡', color: "#66666c", sum: 1382, add: "+1"},
          {name: 'ok', text: '治愈', color: "#178B50", sum: 7094, add: "+366"}
        ],
        mychinaChart: "",
      }
    },
    mounted() {

    },
    methods: {
      onReady(instance) {
        console.log(instance);
      },
      onClick(event, instance, echarts) {
        console.log(arguments[0].name);
        console.log(111)
        this.$emit('chart-click', arguments)
      }
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

