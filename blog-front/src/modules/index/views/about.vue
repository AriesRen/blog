<template>
  <v-layout row pb-2 justify-center>
    <v-flex xs8>
      <v-card class="card--flex-toolbar">
        <v-card-text>
          <!-- <h2>关 于 我</h2>
         
         <p>
           1. 我暗暗就卡复活v哪里开始的复函
         </p>

         <br>
         <h2>本 网 站</h2>
         
         <p>
           1. 我暗暗就卡复活v哪里开始的复函
         </p>

         <br>
         <h2>项 目</h2>
         
         <p>
           1. 我暗暗就卡复活v哪里开始的复函
         </p>
         <br>
         <h2>职 业 生 涯</h2>
         
         <p>
           1. 2017-4-21. 毕业就快要一年的时间了。时间过得飞快，不知道我的那些老同学现在怎么样了。 
说说这个博客吧，这个博客是一个技术型的博客，我把自己掌握的知识到运用到这里，也将自己遇到的问题，都记录下来，方便自己查阅。 
然后我这个个人博客我会一直坚持下去的，也会一直往技术方面走，我觉得单纯为了做博客而用一站式建站没有什么意思，这也是为什么我不想做CMS只做博客的主要原因。 
         </p>
         <br>
         <h2>关 于 网 站</h2>
         
         <p>
           1. 我暗暗就卡复活v哪里开始的复函
          </p>-->

          <el-timeline>
            <el-timeline-item timestamp="2018/4/12" placement="top">
              <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/12 20:46</p>
              </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2018/4/3" placement="top">
              <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/3 20:46</p>
              </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2018/4/2" placement="top">
              <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/2 20:46</p>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {}
};
</script>

<style lang="scss" scoped>
.card--flex-toolbar {
  margin-top: 30px;
}
</style>