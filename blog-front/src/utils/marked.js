import marked from 'marked'
export function mark(markdown, html){
    return marked(markdown)
}