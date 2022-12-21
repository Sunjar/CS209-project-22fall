<template>
<h1>This is the comment {{this.issueNum}} page.</h1>

  <div id="mainTable">
    <el-scrollbar>
      <el-table :data="commentData" max-height="300">

        <el-table-column prop="id" label="ID" width="100" />
        <el-table-column prop="comment" label="Comment" width="1000" />

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
    return{
      issueNum : this.$route.query['detailNum'],
      commentData: [
        {
          id: 1,
          comment: ''
        }
      ],
      pieData:[],
      reply: 0,
      others: 0
    }
  },
  mounted() {
    this.echartsInit()
  },
  methods: {
    echartsInit() {
      let curUrl = 'http://localhost:8084/repo/info/issues/' + this.issueNum + '/comment';
      axios.get(curUrl).then(res => {
        // alert(res.data[0]["comment"])
        this.commentData = res.data
        for (let i = 0; i < res.data.length; i++) {
          this.commentData[i]["comment"] = res.data[i]["comment"]
          this.commentData[i]["id"] = i + 1
          if (res.data[i]["comment"].indexOf('@') !== -1) this.reply++;
          else this.others++;
        }
        this.pieData.push({value:this.reply, name:"replies"})
        this.pieData.push({value:this.others, name:"others"})

        myChart.setOption(option);
      }).catch(err => console.log(err))

      var myChart = echarts.init(document.getElementById('piechart'));
      var option = {
        title: {
          text: 'Comment Analysis',
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
            name: 'Comment count',
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
        ///////////////////////
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