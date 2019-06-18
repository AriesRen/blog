import 'highlight.js/styles/atom-one-dark.css'
import hljs from 'highlight.js'
import Vue from 'vue'

Vue.directive('highlight', (el) => {
    let blocks = el.querySelectorAll("pre code")
    blocks.forEach((block) => {
        hljs.highlightBlock(block)
    })
})