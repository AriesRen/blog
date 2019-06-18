<template>
    <div id="terminal">
    </div>
</template>

<script>
import 'xterm/dist/xterm.css'
import {Terminal} from 'xterm'
export default {
    props: {
        width,
        height,
        color
    },
    data() {
        return{
            
        }
    }
}
</script>

