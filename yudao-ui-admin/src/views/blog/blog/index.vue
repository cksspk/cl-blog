<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label=" 文章标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入文章标题" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label=" 摘要 " prop="summary">
        <el-input v-model="queryParams.summary" placeholder="请输入摘要" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label=" 状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="博客状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label=" 推荐" prop="support">
        <el-input v-model="queryParams.support" placeholder="推荐" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['blog:blog:create']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['blog:blog:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="分类" align="center" prop="categoryId" />
      <el-table-column label="摘要" align="center" prop="summary" />
      <el-table-column label="封面" align="center" prop="headerImgType" />
      <el-table-column label="评论" align="center" prop="comment" />
      <el-table-column label="推荐" align="center" prop="support" >
        <template slot-scope="scope">
          <el-switch v-model="scope.row.support" 
            :active-value="1" :inactive-value="0" 
            @change="handleSupportChange(scope.row)" 
            active-color="#13ce66"
            inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 1">发布</el-tag>
          <el-tag v-else type="warning">草稿</el-tag>
        </template>
      </el-table-column>      
      <el-table-column label=" 权重 " align="center" prop="weight" >
        <template slot-scope="scope">
          <el-rate v-model="scope.row.weight" :max="5" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" disabled
                   :low-threshold="1" :high-threshold="5" style="display:inline-block"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['blog:blog:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['blog:blog:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>
                
  </div>
</template>

<script>
import { createBlog, updateBlog, deleteBlog, getBlog, getBlogPage, changeBlogSupport } from "@/api/blog/blog";
import Editor from '@/components/Editor';

export default {
  name: "Blog",
  components: {
    Editor,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 博客列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        categoryId: null,
        title: null,
        summary: null,
        headerImgType: null,
        headerImg: null,
        htmlContent: null,
        content: null,
        status: null,
        comment: null,
        support: null,
        weight: null,
        like: null,
        click: null,
        createTime: [],
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getBlogPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
    },
    /** 表单重置 */
    reset() {
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push({ path: "/blogManage/blog/create"});
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const blogId = row.id;
      // this.$router.push({ path: "/blogManage/blog/create"});
      this.$router.push({ path: "/blogManage/blog/edit/" + blogId});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateBlog(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createBlog(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除博客编号为"' + id + '"的数据项?').then(function() {
          return deleteBlog(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    handleSupportChange(row) {
      let text = row.support ? "推荐" : "取消推荐";
      this.$confirm('确认要' + text + '"' + row.title + '"博客吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        console.log("row:", row);
        const data = {
          id: row.id,
          support: row.support
        }
        return changeBlogSupport(data);
      }).then((response) => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function () {
        row.support = row.support === 1 ? 0 : 1;
      });
    }
  }
};
</script>
