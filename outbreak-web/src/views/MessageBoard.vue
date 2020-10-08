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
              <h4>{{al.title}}</h4>
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
  export default {
    data() {
      return {
        brandFold: true,
        als: [
          {
            title: "黑龙江省最新疫情通报",
            article: "2020年9月26日0-24时，黑龙江省无新冠肺炎疫情报告。 国内疫情基本平息，全国无中高风险地区。境外新冠肺炎疫情大流行的势头仍未得到有效遏制，输入性疫情的风险长期存在。专家研判今年秋冬季我国新冠肺炎疫情暴发的风险较高，秋冬季又是各类呼吸道疾病的高发期，将增加新冠肺炎疫情形势的复杂性和防控难度。大家仍要紧绷疫情防控常态化这根弦，始终保持防范意识。国庆、中秋“双节”将至，要合理安排出行，做好个人防护，遵守公共场所、旅游景区疫情防控要求。食用进口食品之前要洗干净、烹饪食物要煮熟，一旦出现发热、咳嗽等急性呼吸道症状，要及时到当地定点医疗机构发热门诊就诊。各级医疗机构要毫不松懈地抓好常态化院感防控工作，做好发热门诊管理、预检分诊、医务人员防护工作，重点防范大型农贸批发市场、食品加工厂和肉类海鲜市场服务人员等人群，关注近期返校师生，持续关注人群聚集、密闭、环境卫生差等重点场所，做好各关键环节的消毒和疫情信息报告工作。",
            data: "2020/9/27",
            source: "黑龙江省卫建委官网"
          },
          {
            title: "哈尔滨公布新冠复阳患者行动轨迹 所在楼栋已封闭",
            article: "李某某，男，12岁，学生，常住址：哈尔滨市香坊区中北春城。7月31日，与其母亲（女，42岁）从美国经沈阳入境回国。因其母被沈阳市第六医院诊断为新冠肺炎普通型确诊病例，李某某作为密切接触者，在宾馆集中隔离10天被诊断为新冠肺炎无症状感染者，由其父亲（在沈阳时核酸检测结果呈阴性）陪护在沈阳市第六医院住院观察，核酸检测结果呈阴性后，隔离14天期满，与其父于９月８日自驾返回哈尔滨，按规定居家进行健康管理。期间，李某某一直未上学。９月23日，李某某居家健康管理14天后与其父母到哈尔滨市第六医院预约核酸检测，自述无不适症状。９月24日，李某某新冠病毒核酸检测结果呈阳性，其父母新冠病毒核酸检测结果呈阴性。李某某及母亲（陪护）已转运至哈尔滨市定点医院隔离观察，其父作为密切接触者被送至香坊区集中隔离点医学观察。已排查追踪到李某某的密切接触者9人，全部实施集中隔离医学观察。其他密切接触者排查追踪工作仍在进行中。 目前，哈尔滨市有关部门已完成对李某某家庭环境采样工作，并对其住所、楼道、电梯等进行终末消毒，其居住单元已由社区进行封控管理。（黑龙江卫健委）",
            data: "2020/9/25",
            source: "参考信息"
          },
        ],
        textHeight: null
      }
    },
    mounted() {
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
    methods: {
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
      view() {
        var bu = [{
          title: "超甜来袭！6对战疫新人中俄边境线上办婚礼",
          article: "近日，武警黑龙江省总队黑河支队为因疫情和任务推迟婚期的6对新人，在中俄边境线上举办了一场特殊而又难忘的婚礼。在疫情执行任务期间，官兵们担负着黑河高速公路卡点武装巡逻任务，他们的新娘，有的是医护人员，主动请缨到抗疫一线。祝福他们！",
          data: "2020/9/23",
          source: "人民网"
        },
          {
            title: "黑龙江公布无症状复阳者轨迹",
            article: "2020年9月26日0-24时，黑龙江省无新冠肺炎疫情报告。 国内疫情基本平息，全国无中高风险地区。境外新冠肺炎疫情大流行的势头仍未得到有效遏制，输入性疫情的风险长期存在。专家研判今年秋冬季我国新冠肺炎疫情暴发的风险较高，秋冬季又是各类呼吸道疾病的高发期，将增加新冠肺炎疫情形势的复杂性和防控难度。大家仍要紧绷疫情防控常态化这根弦，始终保持防范意识。国庆、中秋“双节”将至，要合理安排出行，做好个人防护，遵守公共场所、旅游景区疫情防控要求。食用进口食品之前要洗干净、烹饪食物要煮熟，一旦出现发热、咳嗽等急性呼吸道症状，要及时到当地定点医疗机构发热门诊就诊。各级医疗机构要毫不松懈地抓好常态化院感防控工作，做好发热门诊管理、预检分诊、医务人员防护工作，重点防范大型农贸批发市场、食品加工厂和肉类海鲜市场服务人员等人群，关注近期返校师生，持续关注人群聚集、密闭、环境卫生差等重点场所，做好各关键环节的消毒和疫情信息报告工作。",
            data: "2020/9/25",
            source: "人民网"
          },]
        if (bu.length < 3){
          this.brandFold = false
        }
        this.als.push(...bu)
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
