class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        
        // If the color is already the same, no need to process
        if (originalColor == color) return image;
        
        dfs(image, sr, sc, originalColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        int m = image.length;
        int n = image[0].length;

        // Boundary check
        if (r < 0 || c < 0 || r >= m || c >= n) return;

        // If color does not match original, stop
        if (image[r][c] != originalColor) return;

        // Change color
        image[r][c] = newColor;

        // Explore 4 directions
        dfs(image, r + 1, c, originalColor, newColor);
        dfs(image, r - 1, c, originalColor, newColor);
        dfs(image, r, c + 1, originalColor, newColor);
        dfs(image, r, c - 1, originalColor, newColor);
    }
}