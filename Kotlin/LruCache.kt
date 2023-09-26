class LRUCache(capacity: Int) {
    var head:DLinkedNode?=null
    var size:Int=0
    fun get(key: Int): Int {
        if(head==null){
            return -1;
        }  
        var current:DLinkedNode?=head
        if(current?.key==key) return current.value
        while(current!=null && current.key!=key){
            current=current.nextNode
        }
        if(current==null) return -1
        if(current.key==key){
            current.preNode?.nextNode=current.nextNode
            current.nextNode?.preNode=current.preNode
            current.preNode=null
            current.nextNode=head
            return current.value
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        if(head==null){
            head=DLinkedNode(key,value)
            return
        }
        val newNode=DLinkedNode(key,value,nextNode=head)
        head.preNode=newNode
        head= newNode
        size++
        if(size>capacity){
            var current=head
            while(current.nextNode!=null){
                current=current.newNode
            }
            current.preNode.nextNode=null
            current.preNode=null
        }
        
    }

}
data class DLinkedNode(val key:Int,val value:Int,var preNode:DLinkedNode?=null,var nextNode:DLinkedNode?=null);
/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */