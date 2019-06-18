<template>
  <v-footer height="auto" color="primary lighten-1">
    <v-layout justify-center row wrap>
      <!-- <v-btn v-for="link in links" :key="link" color="white" flat round>{{ link }}</v-btn> -->
      <v-flex primary  py-3 text-xs-center white--text xs12>
        &copy;2019 —
        <strong>无名万物</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      links: [
        'Home',
        'About Us',
        'Team',
        'Services',
        'Blog',
        'Contact Us'
      ]
    }
  }
}
</script>
