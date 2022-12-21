<template>
  <el-container class="layout-container-demo" style="height: 500px">
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu :default-openeds="['1', '3']">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><message /></el-icon>Navigator One
            </template>
            <el-menu-item-group>
              <template #title>Group 1</template>
              <el-menu-item index="1-1">Option 1</el-menu-item>
              <el-menu-item index="1-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="1-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>Option4</template>
              <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><icon-menu /></el-icon>Navigator Two
            </template>
            <el-menu-item-group>
              <template #title>Group 1</template>
              <el-menu-item index="2-1">Option 1</el-menu-item>
              <el-menu-item index="2-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="2-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="2-4">
              <template #title>Option 4</template>
              <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><setting /></el-icon>Navigator Three
            </template>
            <el-menu-item-group>
              <template #title>Group 1</template>
              <el-menu-item index="3-1">Option 1</el-menu-item>
              <el-menu-item index="3-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="3-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="3-4">
              <template #title>Option 4</template>
              <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>
<!--Above code is the side bar. Ignore it, just for beautiful.-->

    <el-container>
      <el-header style="text-align: center; font-size: 12px">
        <div class="toolbar">
          <el-dropdown>
            <el-icon style="margin-right: 8px; margin-top: 1px"
            ><setting
            /></el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>View</el-dropdown-item>
                <el-dropdown-item>Add</el-dropdown-item>
                <el-dropdown-item>Delete</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>Hello</span>
        </div>
      </el-header>
<!--Some small components. Ignore it.-->
      <el-main>
        <el-scrollbar>
          <el-table :data="tableData" slot-scope="scope">
            <el-table-column prop="name" label="Name" width="200" />
            <el-table-column prop="id" label="Repo ID" width="200" />
<!--            <el-table-column prop="owner" label="Owner" width="300"/>-->
            <el-table-column label="Opt">
              <template v-slot:default="{row}">
                <el-row>
                  <el-button type="primary" @click="jump_button(row.id, row.name)">Check Detail</el-button>
                </el-row>
            </template>
            </el-table-column>
          </el-table>
        </el-scrollbar>
      </el-main>
      <template>
        <el-row>
          <el-button type="primary">Button1</el-button>
        </el-row>
      </template>
    </el-container>
  </el-container>


    <el-row class="mb-4">
      <el-button>Default</el-button>
      <el-button type="primary">Primary</el-button>
      <el-button type="success">Success</el-button>
      <el-button type="info">Info</el-button>
      <el-button type="warning">Warning</el-button>
      <el-button type="danger">Danger</el-button>
    </el-row>

</template>

<script>
import axios from 'axios'

export default {
  created() {
    axios.get('api/repo/info').then(res => {
      this.tableData = res.data;
    }).catch(err => console.log(err))
  },
  methods: {
    jump_button: function(e, r){

      // alert(JSON.stringify(e))
      // alert(e.id)
      // alert(e)
      this.$router.push({
        path: 'detail',
        query:{
          detailData: e,
          detailName: r
        }
      })
    }
  },
  data() {
    return {
      urlData: '',
      tableData: [
        {
          name: 'abc',
          id: 1,
          owner: 'ABC'
        },
        {
          name: 'bcd',
          id: 2,
          owner: 'BCD'
        }
      ]
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
</style>