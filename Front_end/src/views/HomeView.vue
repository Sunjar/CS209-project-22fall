<template>
  <div id="backcont">
    <p>  </p>
    <el-menu :default-active="activeIndex"
             @select="handleselect"
             mode="horizontal"
    >
      <el-menu-item index="1">{{ eventValue[0] }}</el-menu-item>
      <el-menu-item index="2">{{ eventValue[1] }}</el-menu-item>
      <el-menu-item index="3">{{ eventValue[2] }}</el-menu-item>
      <el-menu-item index="4">{{ eventValue[3] }}</el-menu-item>
    </el-menu>
    <header>
      <h1>2022 Fall CS209 Project</h1>
    </header>

    <section class="itemcenter">
      <div v-if="type==='1'">
        <CrawlerView></CrawlerView>
      </div>
      <div v-else-if="type==='2'">
        <RepoView></RepoView>
      </div>
      <div v-else-if="type==='3'">
        <DetailView></DetailView>
      </div>
      <div v-else-if="type==='4'">
        <TestView></TestView>
      </div>
      <div v-else>
        <h2>
          Welcome to the Web App!
        </h2>
      </div>
    </section>
    <router-view/>
  </div>
</template>

<script>
import RepoView from '@/views/RepoView'
import TestView from "@/views/TestView";
import CrawlerView from "@/views/CrawlerView";
import DetailView from "@/views/DetailView"


export default {
  components: {CrawlerView, RepoView, DetailView, TestView},
  data() {
    return {
      activeIndex: '1',
      eventValue: ['Crawler','Repos','Detail','Test'],
      type: 0,
      tableData: null
    }
  },
  methods: {
    handleselect(key, value) {
      const _this = this;
      _this.type = key;
      _this.tableData = null;
    },
  }
}


</script>

<style>

*{
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
