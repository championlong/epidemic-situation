<template>
  <div class="only">
    <message :title="title" :sums="sums" :updateTime="updateTime"></message>
    <china id="chart" style="width: 100% ;height: 800px;margin-top: 40px" align="center"></china>
  </div>
</template>

<script>
  import message from '@/components/message'
  import map from '@/components/map_china'
  import {getTotalData} from '@/api/epidemic'

  export default {
    name: 'Index',
    created() {
      this.tagList()
    },
    data() {
      return {
        title: "国内疫情",
        sums: [],
        updateTime:"2020-09-27 09:37",
      }
    },
    methods: {
      async tagList() {
        const result = await getTotalData()
        this.sums = result.data.tagList
        this.updateTime = result.data.date
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

