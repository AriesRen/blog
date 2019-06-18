<template>
  <v-container fluid class="container">
    <v-layout row wrap>
      <v-flex xs3>
        <v-card class="scroll-y" :height="fullHeight">
          <v-list two-line class="pt-0 pb-0">
            <template v-for="(item, index) in items">
              <v-list-tile
                :key="item.title"
                avatar
                ripple
                @click="toggle(index)"
                @contextmenu="show($event, item)"
              >
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                  <v-list-tile-sub-title class="text--primary">{{ item.headline }}</v-list-tile-sub-title>
                  <v-list-tile-sub-title>{{ item.subtitle }}</v-list-tile-sub-title>
                </v-list-tile-content>

                <v-list-tile-action>
                  <v-list-tile-action-text>{{ item.action }}</v-list-tile-action-text>
                  <v-icon v-if="selected.indexOf(index) < 0" color="green" @click="save(item)">check</v-icon>

                  <v-icon v-else color="red">save</v-icon>
                </v-list-tile-action>
              </v-list-tile>
              <v-divider :key="index"></v-divider>
            </template>
            <v-list-tile ripple>
              <v-btn block color="indigo" dark>
                <v-icon>add</v-icon>
              </v-btn>
            </v-list-tile>
          </v-list>
        </v-card>
      </v-flex>
      <v-flex xs9>
        <v-card class="scroll-y" :height="fullHeight">
          <v-card-title class="pt-0 pb-0 pl-0 pr-0" color="white">
            <v-toolbar color="white">
              <v-text-field v-model="currentItem.title" hide-details placeholder="标题" solo flat>
              </v-text-field>
              <v-btn flat icon>
                <v-icon>settings</v-icon>
              </v-btn>
            </v-toolbar>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <template>
              <div class="mavonEditor">
                <mavon-editor
                  :defaultOpen="default_open"
                  v-model="currentItem.subtitle"
                  :toolbarsFlag="false"
                  :scrollStyle="true"
                  style="min-height:560px;z-index: 0;"
                />
              </div>
            </template>
          </v-card-text>
        </v-card>
      </v-flex>

      <v-menu
        v-model="showMenu"
        :position-x="menuX"
        :position-y="menuY"
        absolute
        offset-y
        class="elevation-24"
      >
        <v-list class="pt-0">
          <v-list-tile @click="add">
            <v-list-tile-content>
              <v-list-tile-title>新建博客</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon>add</v-icon>
            </v-list-tile-action>
          </v-list-tile>
          <v-divider></v-divider>
          <v-list-tile @click="del">
            <v-list-tile-content>
              <v-list-tile-title>删除博客</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon>delete</v-icon>
            </v-list-tile-action>
          </v-list-tile>
          <v-divider></v-divider>
          <v-list-tile>
            <v-list-tile-content>
              <v-list-tile-title>编辑博客</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon>edit</v-icon>
            </v-list-tile-action>
          </v-list-tile>
          <v-divider></v-divider>
          <v-list-tile>
            <v-list-tile-content>
              <v-list-tile-title>编辑博客</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon>edit</v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-menu>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      showMenu: false,
      toolbars: {
        bold: true,
        italic: true,
        superscript: true,
        mark: true,
        quote: true,
        code: true,
        od: true,
        link: true,
        imagelink: true,
        subfield: true,
        fullscreen: true,
        save: true
      },
      // default_open: "preview",
      default_open: 'edit',
      menuX: 0,
      menuY: 0,
      selected: [0],
      fullHeight: window.innerHeight + "px",
      currentItem: {},
      newItem: {},
      editItem: {},
      items: ""
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    fetch() {
      this.items = [
        {
          action: "15 min 前",
          headline: "Brunch this weekend?",
          title: "Ali Connors",
          subtitle:
            "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
        },
        {
          action: "2 hr",
          headline: "Summer BBQ",
          title: "me, Scrott, Jennifer",
          subtitle: "Wish I could come, but I'm out of town this weekend."
        },
        {
          action: "6 hr",
          headline: "Oui oui",
          title: "Sandra Adams",
          subtitle: "Do you have Paris recommendations? Have you ever been?"
        },
        {
          action: "12 hr",
          headline: "Birthday gift",
          title: "Trevor Hansen",
          subtitle:
            "Have any ideas about what we should get Heidi for her birthday?"
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          title: "Britta Holt",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          title: "Rdasfa asdf ",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        },
        {
          action: "2017/12/07",
          headline: "Recipe to try",
          title: "Diradn asdfasd",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          title: "Dadsaf asdfa",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          title: "Edafd asdfasd",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          title: "FGdafsdf",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos."
        }
      ];
    },
    toggle(index) {
      this.currentItem = this.items[index];
    },
    show(e, item) {
      e.preventDefault();
      this.showMenu = false;
      this.menuX = e.clientX;
      this.menuY = e.clientY;
      this.$nextTick(() => {
        this.showMenu = true;
        this.currentItem = item;
      });
    },
    add(e) {
      console.log(e);
      console.log(this.currentItem);
    },
    del(e) {
      console.log(e.target);
    },
    save(item) {
      console.log(item);
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~mavon-editor/dist/css/index.css";

.container {
  width: 100%;
  height: 100%;
  overflow: auto;
  padding: 0;
}

.container-fluid {
  width: 100%;
  height: 100%;
  padding: 24px;
}
.scroll-y::-webkit-scrollbar {
  width: 5px;
  height: 1px;
}
.scroll-y::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 5px rgba(0, 0, 80, 0.2);
  background: #535353;
}
.scroll-y::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background: #ededed;
}
</style>
