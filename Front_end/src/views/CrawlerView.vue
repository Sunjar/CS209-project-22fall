<template>
  <div id="head" class="common-layout">
      <h2>Hello!</h2>
      <h2>Welcome to the web app.</h2>
      <p>In this page you can enter the willing url to get the information of the repository.</p>
  </div>

    <div>
      <el-form-item label = "URL" class="input-url">
        <el-input v-model="urlData"  size="large" placeholder="Please input the url" />
      </el-form-item>
      <el-button type="primary" plain @click="test_button">Go!</el-button>
    </div>

</template>


<script>
import axios from 'axios'

export default {
  created(){
    const _this = this;
  },
  methods: {
    test_button: function () {
      var formData = new FormData();
      formData.append('url', this.urlData)
      axios.post('/api/add/crawler', formData).then(function (resp) {
        alert("success!")
      })
    }
  },
  data() {
    return {
      urlData: '',
      tableData: null
    }
  }
}
</script>

<style scoped>
.table_vi {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  width: 70%;
}

#head{
  line-height: 40px;
  width: 750px;
  height: 150px;
  background-color: #F9E3AF;
  font-size: 20px;
  margin: 40px auto 0;
  border: 2px solid black;
  opacity: 1;
  /*position: absolute;*/
  top:300px;
  left:500px;
  text-align: center;
}

:deep .input-url{
  width: 80%;
  height: 180%;
  top:500px;
  left:500px;
  text-align: center;
  vertical-align:middle;
}
</style>