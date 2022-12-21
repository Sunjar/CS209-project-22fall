<template>
<h2 style="left:300px">All Issues</h2>
  <div id="mainTable">
    <el-scrollbar>
      <el-table :data="issueData" max-height="300">

        <el-table-column prop="id" label="ID" width="100" />
<!--        <el-table-column prop="true_id" label="TRUE ID" width="100" />-->
        <el-table-column prop="title" label="Title" width="500" />
        <el-table-column prop="state" label="State" width="140" />
<!--        <el-table-column prop="description" label="Description" width="400" />-->
        <el-table-column prop="begin_time" label="Begin Time" width="200" />
        <el-table-column prop="end_time" label="End Time" width="200" />
        <el-table-column label="Opt">
          <template v-slot:default="{row}">
            <el-row>
              <el-button type="primary" @click="jump_button(row.true_id)">Check Comments</el-button>
            </el-row>
          </template>
        </el-table-column>

<!--        <el-table-column  width="200" />-->


      </el-table>
    </el-scrollbar>
  </div>

  <div style="width: 50%;height: 400px" id="piechart">
  </div>

</template>

<script>
import axios from "axios";
import * as echarts from "echarts";

export default {
  name: "echarts",
  created() {
  },
  data() {
    return {
      repoNum: this.$route.query['detailData'],
      repoName: this.$route.query['detailName'],
      issueData: [{
        id: 1,
        true_id: 0,
        title: '',
        state: '',
        description: '',
        begin_time: null,
        end_time: null,
      }],
      questions: 0,
      update: 0,
      related: 0,
      others: 0,
      pieData: []
    }
  },
  mounted() {
    this.echartsInit()
  },
  methods: {
    jump_button: function(e){
      this.$router.push({
        path: 'comments',
        query:{
          detailNum: e
        }
      })
    },

    dateTimeToString: function (date) {
      var y = date.getFullYear();
      var M = date.getMonth() + 1;
      var d = date.getDate();
      var H = date.getHours();
      var m = date.getMinutes();
      var s = date.getSeconds();
      return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " "
          + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m) + ":" + (s < 10 ? ('0' + s) : s);
    },
    echartsInit() {
      let aUrl = 'http://localhost:8084/repo/info/' + this.repoNum + '/issues/titleAnalysis';
      axios.get(aUrl).then(res => {
          this.related = res.data.length
      }).catch(err => console.log(err))

      let curUrl = 'http://localhost:8084/repo/info/' + this.repoNum + '/issues';
      axios.get(curUrl).then(res => {
        this.issueData = res.data
        var reg1 = new RegExp('ADD', 'i')
        var reg2 = new RegExp('UPDATE', 'i')
        var reg3 = new RegExp('FIX', 'i')
        var reg4 = new RegExp('BUMP', 'i')
        var reg5 = new RegExp('HOW', 'i')
        var reg6 = new RegExp('WHY', 'i')
        for (let i = 0; i < this.issueData.length; i++) {
          this.issueData[i]["true_id"] = res.data[i]["id"]
          this.issueData[i]["id"] = i + 1;
          this.issueData[i]["begin_time"] = this.dateTimeToString(new Date(res.data[i]["start_time"]))
          this.issueData[i]["end_time"] = this.dateTimeToString(new Date(res.data[i]["solve_time"]))
          var t = this.issueData[i]["title"]
          var flag = 0;
          if (t.indexOf('?') !== -1 || t.match(reg5) || t.match(reg6)) {this.questions++;flag = 1;}
          if (t.match(reg1) || t.match(reg2) || t.match(reg3) || t.match(reg4)) {this.update++; flag = 1;}
          // if (t.match(this.detailName)) {this.related++; flag = 1;}
          if (flag === 0) this.others++;
        }
        this.others = Math.max(0, this.others - this.related)
        this.pieData.push({value:this.questions, name:"questions"})
        this.pieData.push({value:this.update, name:"updates"})
        this.pieData.push({value:this.related, name:"related"})
        this.pieData.push({value:this.others, name:"others"})

        myChart.setOption(option);
      }).catch(err => console.log(err))
      var myChart = echarts.init(document.getElementById('piechart'));
      var option = {
        title: {
          text: 'Title Analysis',
          subtext: 'A pie chart',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Issue count',
            type: 'pie',
            radius: '50%',
            data: this.pieData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
        ////////////////////////////////////////
      }
    }
  }
}



</script>

<style scoped>

#mainTable{
  width: 80%;
  height: 70%;
  left: 0;
  top: 10px;
  text-align: left;
}

</style>