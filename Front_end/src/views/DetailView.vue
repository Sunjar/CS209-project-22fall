<template>
  <h1>Details of repo {{numData}}, {{this.repoName}}.</h1>

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

  <section class="itemcenter">
    <div v-if="type==='1'">
      <ContriView></ContriView>
    </div>
    <div v-else-if="type==='2'">
      <IssueView></IssueView>
    </div>
    <div v-else-if="type==='3'">
      <CommitView></CommitView>
    </div>
    <div v-else-if="type==='4'">
      <AnalysisView></AnalysisView>
    </div>
    <div v-else>
      <ContriView></ContriView>
    </div>
  </section>
  <router-view/>
  </div>
</template>

<script>
import ContriView from "@/views/ContriView";
import IssueView from "@/views/IssueView";
import CommitView from "@/views/CommitView";
import AnalysisView from "@/views/AnalysisView";
export default {
  components: {ContriView, IssueView, CommitView, AnalysisView},
  created() {
    this.numData = this.$route.query.detailData
    this.repoName = this.$route.query.detailName
  },
  data(){
    return{
      activeIndex: 1,
      eventValue: ['Contributors','Issues','Releases & Commits','Issue Analysis'],
      type: 0,
      numData: null,
      repoName: null
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

<style scoped>

</style>