import java.util.*;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        
        while (!queue.isEmpty()) {
            
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            
            if (n1 == null && n2 == null)
                continue;
            
            if (n1 == null || n2 == null)
                return false;
            
            if (n1.val != n2.val)
                return false;
            
            queue.add(n1.left);
            queue.add(n2.left);
            queue.add(n1.right);
            queue.add(n2.right);
        }
        
        return true;
    }
}