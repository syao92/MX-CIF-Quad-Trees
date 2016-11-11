# MX-CIF-Quad-Trees
MX-CIF Quad Trees

Code found in /src/solutions.

Quad Tree based on the following reference:

G. Kedem. The quad-cif tree: A data structure for hierarchical on-line algorithms. In Proceedings of
the 19th Design Automation Conference, DAC ’82, pages 352–357, Piscataway, NJ, USA, 1982. IEEE
Press.

Description:

In a MX-CIF quadtree, a region is repeatedly subdivided into four equal-size quadrants until
we obtain blocks which do not contain rectangles. As the subdivision takes place, we associate
with each subdivision point a set containing all of the rectangles that strictly intersect the lines
passing through it. A rectangle strictly intersects a line, if the line divides the rectangle into two
rectangles of non-zero area.

For each node in the MX-CIF quadtree, we store the rectangles intersecting the node’s xor
y-axis in a binary tree, instead of storing them in a simple list. The binary tree represents
a successive bisection of the line segment(corresponding to the x or y-axis). Using a binary tree representation instead
of a list representation, we reduce the search effort by avoiding matching to rectangles that are
far away from the query rectangle. A rectangle is put into a node of the binary tree if a dividing
point is inside that rectangle. A node in the binary tree will contain a list of all the rectangles
that contain that dividing point.

Each Node in the MX-CIF quadtree contains the following information:

1. The quadrant it represents
2. NW, NE, SW and SE children.
3. Binary trees for the x and y axes.
4. Count of the number of rectangles contained in the sub tree rooted at this node

Each node in the binary tree contains the following information:

1. The dividing value
2. Left and right children
3. List of rectangles that contains the dividing point.
