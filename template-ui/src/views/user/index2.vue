<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="listQuery.phone" placeholder="手机号码" style="width: 200px;" class="search_input" name="phone" @keyup.enter.native="_search" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="_search(listQuery.phone)">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">添加</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" @click="_delete">删除</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="user_data" border style="width:100%" max-height="800" @selection-change="selections">
        <el-table-column type="selection" width="55" />
        <el-table-column fixed type="hidden" prop="id" label="id" />
        <el-table-column prop="nick_name" label="昵称" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column prop="register_time" label="注册时间" />
        <el-table-column prop="login_time" label="登录时间" />
        <el-table-column prop="status" label="状态">
          <template slot-scope="{row}">
            <span v-if="row.status==1" style="color:#67C23A;">正常</span>
            <span v-if="row.status==2" style="color:#409EFF;">待审核</span>
            <span v-if="row.status==0" style="color:#E6A23C;">异常</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="{row}">
            <el-button type="primary" size="small" @click.native.prevent="editRow(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <!-- <el-pagination :total="13" layout="total, sizes, prev, pager, next, jumper" :page-size="10" :page-sizes="[10, 20, 30, 40]" :current-page="currentPage" /> -->
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'User2',
  components: { Pagination },
  data() {
    return {
      user_data: [],
      checkBoxData: [],
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 5,
        pageSizes: [5, 10, 15, 20],
        phone: '15823145672'
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.user_data = table_list
      this.total = 13

      // Just to simulate the time of the request
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    editRow(row) {
      console.log(row)
    },
    _search(phone) {
      this.listQuery.page = 1
      this.getList()
      this.$message({
        message: phone + '查询成功',
        type: 'success'
      })
    },
    add() {
      setTimeout(() => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
      }, 3000
      )
    },
    _delete() {
      console.log(this.checkBoxData)
      this.$message({
        message: '删除成功',
        type: 'success'
      })
    },
    selections(val) {
      this.checkBoxData = val
    }
  }

}

const table_list = [{
  id: 1,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '1'
},
{
  id: 2,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '2'
},
{
  id: 3,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '1'
},
{
  id: 4,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '1'
},
{
  id: 5,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '0'
},
{
  id: 6,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '2'
},
{
  id: 7,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '0'
},
{
  id: 8,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '1'
},
{
  id: 9,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '2'
},
{
  id: 10,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '2'
},
{
  id: 11,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '1'
},
{
  id: 12,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '0'
},
{
  id: 13,
  nick_name: '熊波波',
  sex: '男',
  age: '18',
  phone: '12345678910',
  register_time: '2020-07-08 15:25:21',
  login_time: '2020-07-08 17:25:21',
  status: '2'
}]
</script>

<style scoped>

</style>
