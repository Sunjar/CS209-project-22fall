<template>
  <div style="color: cornflowerblue">
  <h2>The total number of release is {{this.releaseNum}}</h2>
    <h2>The total number of commit is {{this.commitNum}}</h2>
  </div>

  <div style="width: 50%; float: left; overflow: hidden">
    <div style="width: 100%;height: 300px" id="barchart">
    </div>
  </div>

  <div style="width: 50%; float: left; overflow: hidden">
    <div style="width: 100%;height: 300px" id="barchart1">
    </div>
  </div>

    <div style="width: 100%;height: 600px;left : 0; top: 200px" id="linechart">
    </div>

</template>

<script>
import * as echarts from "echarts";
import axios from "axios";
export default {
  name: "echarts",
  data(){
    return{
      lineData: [],
      lineNameData: ['asdfasd','asdfasdf'],
      releaseNum: 2,
      barData: [],
      repoNum: this.$route.query['detailData'],
      numData: [],
      commitNum: 100,
      hourData: [2,3],
      hourNameData:['morning','night'],

    }
  },
  created(){

  },
  mounted() {
    this.echartsInit0()
    this.echartsInit1()
    this.echartsInit()
  },
  methods:{
    echartsInit0(){
      let releaseUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/release'
      axios.get(releaseUrl).then((res)=>{
          this.releaseNum = res.data
          // alert(this.releaseNum)
      }).catch(err => console.log(err))

      let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/getCommitByRelease'
      axios.get(curUrl).then((res) =>{
        let cnt = 0
        for(let i = 0;i < res.data.length; i++)
        {
          this.numData[i] = i + 1
          this.barData[i] = res.data[i]
          cnt += this.barData[i]
        }
        this.commitNum = cnt
        // alert(this.barData[2])
        myChart0.setOption(option0)
      }).catch(err => console.log(err))

      var myChart0 = echarts.init(document.getElementById('barchart'));
      var option0 = {
        title:{
          text: 'Commit Times between Releases',
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
          data: this.numData,
          axisLabel:{
            fontWeight: 900,
            color: 'black'
          }
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
        ////////////////////////////////////
      }
    },
    echartsInit1(){
      let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/getCommitByHour'
      axios.get(curUrl).then((res) =>{
        for(let i = 0;i < res.data.length; i++)
        {
          this.hourNameData[i] = res.data[i]["Time_interval"]
          this.hourData[i] = res.data[i]["commit_num"]
        }
        // alert(this.barData[2])
        myChart1.setOption(option1)
      }).catch(err => console.log(err))
      var myChart1 = echarts.init(document.getElementById('barchart1'));
      var option1 = {
        title: {
          text: 'Commit Times by Time period in one day',
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
          data: this.hourNameData,
          axisLabel:{
            fontWeight: 900,
            color: 'black'
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: this.hourData,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(220, 220, 220, 0.8)'
          }
        }]
      }
      ////////////////////////////
    },
    echartsInit(){
        let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/getCommitByDay'
        axios.get(curUrl).then((res) => {
            for(let i = 0;i < res.data.length;i++){
              this.lineData[i] = res.data[i]["commitNum"]
              this.lineNameData[i] = res.data[i]["data"]
            }
            myChart.setOption(option)
        }).catch(err => console.log(err))
        var myChart = echarts.init(document.getElementById('linechart'));
        var option = {
          xAxis: {
            type: 'category',
            data: this.lineNameData,
            axisLabel:{
              fontWeight: 900,
              color: 'black'
            }
          },
          yAxis: {
            type: 'value'
          },
          tooltip: {
            trigger: 'axis'
          },
          grid:{
            top: '20%',
            bottom: '5%'
          },
          series: [
            {
              data: this.lineData,
              type: 'line',
              name: 'commit'
            }
          ]
        }
    }
  }
}
</script>

<style scoped>

</style>