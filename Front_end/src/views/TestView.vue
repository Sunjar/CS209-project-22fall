<template>
  <div style="width: 50%;height: 400px" id="main">
  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "echarts",
  data() {
    return {}
  },
  mounted() {
    this.echartsInit()
  },
  methods:{
    echartsInit() {　　　　　//使用时只需要把setOption里的对象换成echarts中的options或者自己的参数即可
      echarts.init(document.getElementById('main')).setOption({
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [120, 200, 150, 80, 70, 110, 130],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(220, 220, 220, 0.8)'
          }
        }]
      })
    }
  }
}
// echartsInit(){
//   let curUrl = 'http://localhost:8084/repo/info/'+this.repoNum+'/getCommitByDay'
//   axios.get(curUrl).then((res) =>{
//     let data = [[]];
//     let curBase = 0;
//     let oneDay = 24 * 3600 * 1000;
//
//     for(let i = 0;i < res.data.length; i++)
//     {
//       let a = res.data[i]["data"];
//       let curCommit = res.data[i]["commitNum"];
//       // let curDate = new Date(a.replace(/-/g,'/'));
//       let curDate = a.split("-")
//       let year = parseInt(curDate[0])
//       let month = parseInt(curDate[1])
//       let day = parseInt(curDate[2])
//       let base = +new Date(year, month, day);
//       if(i === 0) {
//         curBase = base
//         alert(curBase)
//       }
//       else {
//         // alert('@' + i)
//         while(curBase <= base - 2 * oneDay){
//           curBase = curBase + oneDay;
//           // alert('!!!'+ curBase)
//           data.push([curBase, 0])
//         }
//       }
//       // alert('$$$' + base)
//       data.push([base, curCommit]);
//       curBase = base;
//     }
//     for(let i = 0;i < data.length;i++){
//       // alert(data[i])
//     }
//     this.lineData = data
//     myChart.setOption(option)
//   }).catch(err => console.log(err))
//
//   var myChart = echarts.init(document.getElementById('linechart'));
//   var option = {
//     tooltip: {
//       trigger: 'axis',
//       position: function (pt) {
//         return [pt[0], '10%'];
//       }
//     },
//     title: {
//       left: 'center',
//       text: 'Large Ara Chart'
//     },
//     toolbox: {
//       feature: {
//         dataZoom: {
//           yAxisIndex: 'none'
//         },
//         restore: {},
//         saveAsImage: {}
//       }
//     },
//     xAxis: {
//       type: 'time',
//       boundaryGap: false
//     },
//     yAxis: {
//       type: 'value',
//       boundaryGap: [0, '100%']
//     },
//     dataZoom: [
//       {
//         type: 'inside',
//         start: 0,
//         end: 20
//       },
//       {
//         start: 0,
//         end: 20
//       }
//     ],
//     series: [
//       {
//         name: 'Commit count',
//         type: 'line',
//         smooth: true,
//         symbol: 'none',
//         areaStyle: {},
//         data: this.lineData
//       }
//     ]
//   }
////////////////////////////////
</script>

