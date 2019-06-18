<template>
    <post-detail :post="post"></post-detail>
</template>

<script>
import PostDetail from '../components/post/post-detail'
import {getPostById} from '../api/blogs'
export default {
    components: {
        PostDetail
    },
    data () {
        return {
            postId: this.$route.params.id,
            post: ''
        }
    },
    mounted(){
        this.fetchData()
    },
    methods: {
      fetchData() {
          getPostById(this.postId).then(res => {
              this.post = res.data
          })
      }  
    }
}
</script>
