<template>
  <div style="color: cornflowerblue">
    <h2>The total number of contributors is {{peopleNum}}.</h2>
  </div>

  <div id="mainTable">
    <el-scrollbar>
    <el-table :data="contriData" style="width: 100%" max-height="250">

      <el-table-column prop="id" label="ID" width="100" />
      <el-table-column prop="user_name" label="Name" width="120" />
      <el-table-column prop="contributions" label="Contributions" width="140" />
      <el-table-column prop="avatar_url" label="Avatar" width="400" />
      <el-table-column prop="html_url" label="Html" width="320" >
<!--        <template v-slot:default="props">-->
<!--          <el-link type="primary" @click="handle(props.row)" :underline="false">{{props.row["html_url"]}}</el-link>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column width="200"></el-table-column>

    </el-table>
    </el-scrollbar>
  </div>

  <div id="main">
  </div>



</template>

<script>
import * as echarts from "echarts";
import axios from "axios";

export default {
  name: 'echarts',
  created() {

  },
  data(){
    return{
        barData: [123],
        repoNum: this.$route.query['detailData'],
        peopleNum: 10,
        nameData: ['asda'],
        contriData:[]
    }
  },
  mounted() {
    this.echartsInit()
  },
  methods: {
    echartsInit() {　　　　　
      let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/developers'
      // alert(curUrl)
      axios.get(curUrl).then(res => {
        this.contriData = res.data;
        this.peopleNum = this.contriData.length;
        for(let i = 0;i < this.contriData.length;i++) this.contriData[i]["id"] = i + 1;

        for(let i = 0;i < Math.min(5,this.peopleNum);i++){
          this.barData[i] = this.contriData[i]["contributions"]
          this.nameData[i] = this.contriData[i]["user_name"]
        }
        myChart.setOption(option);
      }).catch(err => console.log(err))

      var myChart = echarts.init(document.getElementById('main'));
      var option = {
        title:{
          text: 'Top 5 contributions ranking',
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
          data: this.nameData
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
        //////////////////////////////////////////////
      }
    },
    handle: function (e){
      let url = e["html_url"]
      window.location.href= url
    }
  }
}
</script>

<style scoped>

#main{
  width: 60%;
  height: 400px;
  left: 300px;
  top: 10px;
  text-align: center;
}

#mainTable{
  width: 80%;
  height: 70%;
  left: 300px;
  top: 10px;
  /*text-align: center;*/
}

</style>