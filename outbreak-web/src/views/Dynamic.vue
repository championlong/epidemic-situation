<template>
  <div style="width: 75%">
    <el-timeline>
      <el-timeline-item :timestamp=al.data placement="top" v-for="(al, index) in als" :key="index">
        <el-card>
          <div ref="more">
          </div>
          <div>
            <div :class="{'retract': al.status}" :style="{'max-height':al.status ? textHeight: ''}"
                 ref="textContainer">
              <el-link :href=al.sourceUrl target="_blank" :underline="false"><h4>{{al.title}}</h4></el-link>
              <p>
                {{al.article}}
              </p>
            </div>
            <br/>
            信息来源：{{al.source}}
            <div class="btn">
              <p v-if="al.status" @click="al.status = false">展开</p>
              <p v-if="al.status == false" @click="al.status = true">收起</p>
            </div>
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>
    <div v-if="brandFold === true" style="text-align:center">
      <el-button type="info" style="width: 100%" @click="view">点击查看更多</el-button>
    </div>
  </div>
</template>

<script>
  import {getList} from '@/api/dynameic'

  export default {
    data() {
      return {
        page:1,
        pageSize:5,
        brandFold: true,
        als: [],
        textHeight: null
      }
    },
    created(){
      this.getList()
    },
    methods: {
      async getList() {
        const result = await getList(this.page,this.pageSize)
        this.als = result.data
        this.show()
      },
      show(){
        this.als.forEach((ele, index) => {
          this.$set(this.als, index, Object.assign({}, ele, {status: null}))
        })
        // DOM 加载完执行
        this.$nextTick(() => {
          this.calculateText()

        })

        window.onresize = () => {
          this.als.forEach((ele, index) => {
            this.$set(this.als, index, Object.assign({}, ele, {status: null}))
          })
          setTimeout(() => {
            this.calculateText()
          }, 0)
        }
      },
      // 计算文字 显示展开 收起
      calculateText() {
        // 获取一行文字的height 计算当前文字比较列表文字
        let oneHeight = this.$refs.more.scrollHeight
        let twoHeight = oneHeight * 5 || 100
        this.textHeight = `${twoHeight}px`
        let txtDom = this.$refs.textContainer
        for (let i = 0; i < txtDom.length; i++) {
          let curHeight = txtDom[i].offsetHeight
          if (curHeight > twoHeight) {
            this.$set(this.als, i, Object.assign({}, this.als[i], {status: true}))
          } else {
            this.$set(this.als, i, Object.assign({}, this.als[i], {status: null}))
          }
        }
      },

      async view() {
        var bu = []
        this.page ++
        const result = await getList(this.page,this.pageSize)
        bu = result.data
        if (bu.length <= 3){
          this.brandFold = false
        }
        this.als = this.als.concat(bu)
        this.show()
      }
    }
  }
</script>

<style scoped>
  .retract {
    position: relative;
    overflow: hidden;
  }

  .retract:after {
    content: '...';
    position: absolute;
    bottom: 0;
    right: 2px;
    width: 25px;
    padding-left: 30px;
    background: linear-gradient(to right, transparent, #fff 45%);
  }

  .btn {
    position: absolute;
    right: 0;
    bottom: 0px;
    font-size: 14px;
    line-height: 19px;
    letter-spacing: 2px;
    color: #5FB878;
    cursor: pointer;
  }


</style>
