<template>
  <v-container grid-list-xl>
    <v-layout align-center row wrap>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <!-- <v-card-title>
            <span class="headline font-weight-medium">分类</span>
          </v-card-title>
          <v-divider/>-->
          <v-card-text class="body-2 pl-5">
            <v-timeline align-top dense>
             
                <v-timeline-item small color="grey" fill-dot right class="animated fadeIn">
                  <v-subheader class="pl-0 headline font-weight-bold black--text text-capitalize animated fadeIn">2019 may</v-subheader>
                  <div class="body-2 black--text font-weight-bold pt-2 animated fadeIn">总共有13篇文章</div>
                </v-timeline-item>
              
              
                <v-timeline-item color="green" small fill-dot  v-for="n in 10" :key="n" class="animated fadeIn">
                  <div class="body-2 black--text font-weight-bold pt-2 text-capitalize animated fadeIn">redis 分布式应用</div>
                </v-timeline-item>
              
            </v-timeline>
            <v-timeline align-top dense>
              
                <v-timeline-item small color="grey" fill-dot right class="animated fadeIn">
                  <v-subheader class="pl-0 headline font-weight-bold black--text text-capitalize animated fadeIn">2019 may</v-subheader>
                  <div class="body-2 black--text font-weight-bold pt-2 animated fadeIn">总共有13篇文章</div>
                </v-timeline-item>
              
              
                 <v-timeline-item color="green" small fill-dot v-for="n in 10" :key="n" class="animated fadeIn">
                  <div class="body-2 black--text font-weight-bold pt-2 text-capitalize animated fadeIn">漏洞靶场Vulhub使用</div>
                </v-timeline-item>
            
            </v-timeline>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <v-card-actions>
            <v-btn depressed flat color="indigo" dark>
              <v-icon left>arrow_back</v-icon>
              <span>上一页</span>
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn depressed flat color="indigo" dark>
              <v-icon left>arrow_forward</v-icon>
              <span>下一页</span>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
