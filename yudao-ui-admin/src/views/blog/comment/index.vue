<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评论昵称" prop="nickName">
        <el-input v-model="queryParams.nickName" placeholder="请输入昵称" clearable @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['blog:comment:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['blog:comment:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="IP地址" align="center" prop="ip" />
      <el-table-column label="操作地点" align="center" prop="location" />
      <el-table-column label="显示" align="center" prop="display" />
      <el-table-column label="内容" align="center" prop="content" :show-overflow-tooltip="true"/>
      <el-table-column label="点赞" align="center" prop="good"/>
      <el-table-column label="踩" align="center" prop="bad"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['blog:comment:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['blog:comment:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="Email地址" prop="email">
          <el-input v-model="form.email" placeholder="请输入Email地址" />
        </el-form-item>
        <el-form-item label="IP地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址" />
        </el-form-item>
        <el-form-item label="地理位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入地理位置" />
        </el-form-item>
        <el-form-item label="系统" prop="os">
          <el-input v-model="form.os" placeholder="请输入系统" />
        </el-form-item>
        <el-form-item label="浏览器" prop="browser">
          <el-input v-model="form.browser" placeholder="请输入浏览器" />
        </el-form-item>
        <el-form-item label="父评论的id" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父评论的id" />
        </el-form-item>
        <el-form-item label="QQ号码" prop="qqNum">
          <el-input v-model="form.qqNum" placeholder="请输入QQ号码" />
        </el-form-item>
        <el-form-item label="头像地址" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像地址" />
        </el-form-item>
        <el-form-item label="博客编号" prop="blogId">
          <el-input v-model="form.blogId" placeholder="请输入博客编号" />
        </el-form-item>
        <el-form-item label="页面的URL" prop="url">
          <el-input v-model="form.url" placeholder="请输入页面的URL" />
        </el-form-item>
        <el-form-item label="1表示显示,0表示不显示" prop="display">
          <el-input v-model="form.display" placeholder="请输入1表示显示,0表示不显示" />
        </el-form-item>
        <el-form-item label="点赞" prop="good">
          <el-input v-model="form.good" placeholder="请输入点赞" />
        </el-form-item>
        <el-form-item label="踩" prop="bad">
          <el-input v-model="form.bad" placeholder="请输入踩" />
        </el-form-item>
        <el-form-item label="评论内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="html内容">
          <editor v-model="form.htmlContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="接收回复邮件" prop="reply">
          <el-input v-model="form.reply" placeholder="请输入接收回复邮件" />
        </el-form-item>
        <el-form-item label="回复类型（1：站长回复 2：不是站长回复）" prop="adminReply">
          <el-input v-model="form.adminReply" placeholder="请输入回复类型（1：站长回复 2：不是站长回复）" />
        </el-form-item>
        <el-form-item label="回复的编号" prop="replyId">
          <el-input v-model="form.replyId" placeholder="请输入回复的编号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createComment, updateComment, deleteComment, getComment, getCommentPage, exportCommentExcel } from "@/api/blog/comment";
import Editor from '@/components/Editor';

export default {
  name: "Comment",
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
      // 评论列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        nickName: null,
        email: null,
        ip: null,
        location: null,
        os: null,
        browser: null,
        parentId: null,
        qqNum: null,
        avatar: null,
        blogId: null,
        url: null,
        display: null,
        good: null,
        bad: null,
        content: null,
        htmlContent: null,
        reply: null,
        adminReply: null,
        replyId: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
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
      getCommentPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        nickName: undefined,
        email: undefined,
        ip: undefined,
        location: undefined,
        os: undefined,
        browser: undefined,
        parentId: undefined,
        qqNum: undefined,
        avatar: undefined,
        blogId: undefined,
        url: undefined,
        display: undefined,
        good: undefined,
        bad: undefined,
        content: undefined,
        htmlContent: undefined,
        reply: undefined,
        adminReply: undefined,
        replyId: undefined,
      };
      this.resetForm("form");
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
      this.reset();
      this.open = true;
      this.title = "添加评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getComment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateComment(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createComment(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除评论编号为"' + id + '"的数据项?').then(function() {
          return deleteComment(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有评论数据项?').then(() => {
          this.exportLoading = true;
          return exportCommentExcel(params);
        }).then(response => {
          this.$download.excel(response, '评论.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
