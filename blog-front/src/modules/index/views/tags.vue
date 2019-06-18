<template>
  <v-container grid-list-xl>
    <v-layout align-center row wrap>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <!-- <v-card-title>
            <el-divider>
              <span class="headline">标签</span>
            </el-divider>
          </v-card-title>-->
          <v-card-text>
            <div style="text-align:center;">
              <h2>标签</h2>
            </div>
            <v-layout>
              <v-flex xs12 sm12>
                <v-container grid-list-lg>
                  <v-layout row wrap justify-center>
                    <v-btn
                      outline
                      color="indigo"
                      v-for="(tag,index) in tags"
                      :key="index"
                      :to="tag.link"
                    >{{tag.name}} {{tag.value}}</v-btn>
                  </v-layout>
                </v-container>
              </v-flex>
            </v-layout>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <v-card-text class="body-2">
            <div id="echarts" style="height:350px;width:100%; margin: 20px auto;"></div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
let echarts = require("echarts/lib/echarts");
require("echarts/lib/chart/pie");
require("echarts/lib/component/tooltip");
require("echarts/lib/component/title");
import {getTags} from '../api/tags'
export default {
  data() {
    return {
      current: 1,
      size: 10,
      total: 0,
      hasNextPage: false,
      hasPreviousPage: false,
      isFirstPage: false,
      isLastPage: false,
      pages: 0,
      tags: [
        {
          name: "编程之道",
          value: "3",
          code: "program",
          link: "/tags/program"
        },
        {
          name: "数据库",
          value: "16",
          code: "database",
          link: "/tags/database"
        },
        {
          name: "Python",
          value: "10",
          code: "python",
          link: "/tags/python"
        },
        {
          name: "Java",
          value: 80,
          code: "java",
          link: "/tags/java"
        },
        {
          name: "算法",
          value: 60,
          code: "algothim",
          link: "/tags/algothim"
        },
        {
          name: "Linux",
          value: 5,
          code: "linux",
          link: "/tags/linux"
        },
        {
          name: "安全",
          value: 10,
          code: "security",
          link: "/tags/security"
        },
        {
          name: "生活",
          value: 2,
          code: "life",
          link: "/tags/life"
        },
        {
          name: "面试",
          value: 10,
          code: "interview",
          link: "/tags/interview"
        },
        {
          name: "Go语言编写个人博客",
          value: 10,
          code: "go",
          link: "/tags/go"
        }
      ]
    };
  },
  mounted() {
    this.getAllTags(this.current, this.size)
    this.draw();
  },
  methods: {
    getAllTags(current, size) {
      getTags(current, size).then(res=> {
        console.log(res)
      })
    },
    draw() {
      var _this = this;
      let chart = echarts.init(document.getElementById("echarts"));

      chart.setOption({
        theme: "light",
        tooltip: {
          trigger: "item",
          formatter: function(obj) {
            return (
              obj.seriesName +
              "\n" +
              obj.name +
              " : " +
              obj.value.value +
              "(" +
              obj.percent +
              ")"
            );
          }
        },
        legend: {
          orient: "vertical",
          show: true,
          left: 100,
          formatter: function(obj) {
            return echarts.format.truncateText(
              obj.name,
              40,
              "14px Microsoft Yahei",
              "…"
            );
          }
        },
        dataset: {
          source: this.tags
        },
        series: [
          {
            name: "标签",
            type: "pie",
            startAngle: 3,
            label: {
              show: true,
              formatter: function(obj) {
                return obj.name + " : " + obj.value.value;
              },
              fontSize: 13
            },
            itemStyle: {
              emphasis: {
                shadowBlur: 5,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      });

      chart.on("click", function(params) {
        _this.$router.push(params.data.link);
      });
    }
  }
};
</script>
