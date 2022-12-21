<template>
<h1>This is the issue view.</h1>

  <div style="width: 50%; float: left; overflow: hidden">
    <div style="width: 50%;height: 400px" id="piechart">
    </div>
  </div>
  <div style="width: 50%; float: left; overflow: hidden">
    <div style="width: 50%;height: 400px" id="barchart">
    </div>
  </div>

  <div id="head" class="common-layout">
    <h2>The variance is {{this.variance}}</h2>
    <h2>The stddev is {{this.stedev}}</h2>
  </div>

</template>

<script>
import * as echarts from "echarts";
import axios from "axios";

export default {
  name: "echarts",
  created(){

  },
  data(){
    return{
      barData: [],
      repoNum: this.$route.query['detailData'],
      pieData: [],
      avgData: 9.8,
      minData: 23.3,
      maxData: 23.5,
      extData: 223.4,
      variance: 0,
      stedev: 0
    }
  },
  mounted() {
    this.echartsInit()
    this.echartsInit1()
  },

  methods:{
    echartsInit(){
      let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/issues/state';
      axios.get(curUrl).then(res => {
        for(let i = 0;i < res.data.length;i++)
        {
          this.pieData.push({value:res.data[i]["issue_num"], name:res.data[i]["state"] + " issues"})
        }
        myChart.setOption(option);
      }).catch(err => console.log(err))

      var myChart = echarts.init(document.getElementById('piechart'));
      var option = {
        title: {
          text: 'Open and Close Issues',
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

        /////////////////////////////////////
      }
    },
    echartsInit1(){
      let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/issues/analysis';
      axios.get(curUrl).then(res => {
        for(let i = 0;i < res.data.length;i++)
        {
          this.avgData = res.data[0]["avg"]
          this.minData = res.data[0]["min"] + 1
          this.maxData = res.data[0]["max"]
          this.extData = res.data[0]["extremum"] - 1
          this.variance = res.data[0]["variance"]
          this.stedev = res.data[0]["stedev"]
        }
        this.barData.push(this.avgData)
        this.barData.push(this.minData)
        this.barData.push(this.maxData)
        this.barData.push(this.extData)
        myChart1.setOption(option1);
      }).catch(err => console.log(err))

      var myChart1 = echarts.init(document.getElementById('barchart'));
      var option1 = {
        title:{
          text: 'Some attributes of issues',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: ['average', 'min value', 'max value', 'extreme']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: this.barData,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(220, 220, 220, 0.8)'
          }
        }]
      }
    }
  }
}
</script>

<style scoped>

#head{
  line-height: 40px;
  width: 750px;
  height: 100px;
  background-color: #F9E3AF;
  font-size: 20px;
  margin: 40px auto 0;
  border: 2px solid black;
  opacity: 1;
  /*position: absolute;*/
  top:300px;
  left:800px;
  overflow: hidden;
  text-align: center;
}

</style>