<template>
  <v-layout justify-center>
    <v-flex xs12 sm10 md10>
      <v-container grid-list-lg fluid>
        <v-layout row wrap>
          <v-flex v-for="n in 20" :key="n" xs12 sm6 md4 class="animated fadeIn">
            <friend></friend>
          </v-flex>
        </v-layout>
      </v-container>
    </v-flex>
  </v-layout>
</template>

<script>
import Friend from '../components/friend'
export default {
  components: {
    Friend
  },
}
</script>
