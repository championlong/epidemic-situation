<template>
  <div>
    <div style="text-align: left">
      <div style="text-align: center;margin-top: 30px">
        <h2>疫情期间防聚集便民系统</h2>
      </div>
      <div style="height: 40px; line-height: 40px">
        <label style="font-weight: 800; font-size: 18px">{{title}}</label>
        <label style=" color:#C0C0C0">数据更新时间： {{updateTime}}</label>
      </div>
    </div>
    <!-- 总体数据汇总情况 -->
    <el-row style="margin-top:50px">
      <el-col :span="4" v-for="item in sums" :key="item.name">
        <div class="grid-content " align="center">
          <div class="sum_numb" :style="{color: item.color, fontSize: '20px', marginBottom: '8px'}">{{item.sum}}</div>
          <div class="sum_numb" style="color: #333333; font-size: 14px">{{item.text}}</div>
          <div class="sum_numb" style="font-size: 13px; color: #999999; margin-top: 8px">
            <label style="font-weight:200">昨日 </label>
            <label :style="{color: item.color}" v-if="item.add>=0">+{{item.add}}</label>
            <label :style="{color: item.color}" v-else>{{item.add}}</label>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  // import {Utils} from "../js/utils";

  export default {
    name: 'message',
    props: ['title', 'updateTime', 'sums']
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .grid-content {
    border-radius: 4px;
    min-height: 46px;
  }

  .sum_numb {
    height: 20px;
    font-weight: 700;
  }


</style>
