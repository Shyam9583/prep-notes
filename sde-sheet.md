# Striver's SDE Sheet — Revision Notes

> **Format:** `- [ ]` = not done · `- [x]` = done · Add intuition/notes after the `→`

---

## Arrays & Hashing — 24 Problems

### Part I
- [x] Set Matrix Zeroes → Use first row & col as markers. Track first-col separately (`firstColZero` flag) since `matrix[0][0]` is shared. Pass 1: mark rows/cols. Pass 2: zero out inner cells. Pass 3: handle first row, then first col last.
- [x] Pascal's Triangle → Each cell = sum of two cells above. Use a `get(prev, j)` helper that returns 0 for out-of-bounds indices — cleanly handles the edge 1s without special-casing.
- [x] Next Permutation → 3 steps: (1) find rightmost breakpoint where `nums[i] < nums[i+1]`; (2) swap it with the rightmost element greater than it; (3) reverse the suffix after breakpoint. If no breakpoint, array is fully descending — just reverse all.
- [x] Kadane's Algorithm → Running sum `sumTillNow`; update global max before resetting. Reset to 0 when sum goes negative (a negative prefix can only hurt). Init global max to `MinInt` to handle all-negative arrays.
- [x] Sort Colors (0s 1s 2s) → Dutch National Flag: 3 pointers `left/mid/right`. 0→swap with left, advance both; 2→swap with right, decrement right only (don't advance mid, swapped val is unexamined); 1→just advance mid.
- [x] Best Time to Buy and Sell Stock → Sliding window: `l` = best buy day, `r` scans forward. If `prices[r] > prices[l]`, update max profit. Else move `l = r` (found a cheaper buy day — no point keeping old left).

### Part II
- [x] Rotate Image (Matrix) → 90° clockwise = transpose + reverse each row. Transpose swaps `[i][j]` with `[j][i]` (upper triangle only, start `j=i` to avoid double-swap). Anti-clockwise = reverse each row + transpose.
- [x] Merge Overlapping Subintervals → Sort by start time. Maintain last interval in result; if `prev[1] >= curr[0]`, merge in-place by extending `prev[1]`. Since `prev` is a slice reference into `result`, no re-append needed.
- [x] Merge Sorted Array → Fill `nums1` from the back using 3 pointers: `i=m-1`, `j=n-1`, `k=m+n-1`. Place the larger of `nums1[i]`/`nums2[j]` at `k`. Remaining `nums2` elements need copying; remaining `nums1` elements are already in place.
- [x] Find the Duplicate Number → Floyd's cycle detection on the array as a linked list (value = next index). Phase 1: find intersection point. Phase 2: reset slow to 0, advance both one step — they meet at the cycle entry = duplicate.
- [x] Repeat and Missing Number → Math: let x=repeat, y=missing. Compute `S = Σ(arr[i]-i)` → gives `x-y`. Compute `S2 = Σ(arr[i]²-i²)` → gives `x²-y²= (x+y)(x-y)`, so `x+y = S2/S`. Solve: `x=(S+S2/S)/2`, `y=(S2/S-S)/2`.
- [x] Inversion of Array → Merge sort variant. During merge, when `left[i] > right[j]`, all remaining elements in left half also form inversions with `right[j]` → add `leftHalf.length - i`. Count inversions while sorting in O(n log n).

### Part III
- [x] Search a 2D Matrix → Treat as flattened 1D sorted array. Binary search on `[0, m*n-1]`; map index back via `row = mid/n`, `col = mid%n`. Works because rows are sorted and last element of each row < first of next.
- [x] Pow(x, n) → Binary exponentiation: iterate over bits of `|n|`; if bit is set, multiply result by current `mult`; square `mult` each iteration. O(log n). Negative n → return `1/result`. Watch out: `math.MinInt` overflow when negating — handle separately.
- [x] Majority Element (N/2) → Boyer-Moore Voting: maintain a candidate and count. Same element → increment, different → decrement. Reset candidate when count hits 0. Majority element always survives because it outnumbers all others combined.
- [x] Majority Element (N/3) → Extended Boyer-Moore with a map: keep at most 2 candidates. When map size exceeds 2, decrement all counts and delete zeroes (cancellation step). Then recount candidates in a second pass to verify they actually exceed n/3.
- [x] Grid Unique Paths → DP: paths at `[i][j]` = right + down. Space-optimised to 1D column: fill bottom-up, each cell = `currCol[i+1] + nextCol[i]`. Last row is always 1 (only one way: go all right). Roll columns right to left.
- [x] Reverse Pairs → Merge sort variant. Count cross-pairs (`left[i] > 2*right[j]`) in `countPairs` *before* merging (merge destroys order). Two-pointer in `countPairs` works because both halves are sorted — `j` never resets, giving O(n) per level.

### Part IV
- [x] 2-Sum Problem → HashMap stores `value → index`. For each element, check if `target - num` already seen. Store after lookup to avoid using same index twice.
- [x] 4-Sum Problem → Sort + fix two pointers `i`, `j`, then two-pointer `k/l` for the inner pair. Skip duplicates at each level after processing. O(n³). Same pattern generalises to k-sum by adding more outer loops.
- [x] Longest Consecutive Sequence → Add all elements to a HashSet. Only start counting from sequence heads (`item-1` not in set). Extend with `item+1` until chain breaks. O(n) — each element is visited at most twice.
- [x] Largest Subarray with 0 Sum → Prefix sum + HashMap. Subarray `[l+1..r]` sums to k iff `prefix[r] - prefix[l] == k`. Store earliest index of each prefix sum (`putIfAbsent`); lookup `prefix[r] - k` to find longest span.
- [x] Subarrays with XOR K → Prefix XOR + HashMap (count of occurrences). Subarray `[l+1..r]` has XOR k iff `prefix[r] ^ prefix[l] == k` → lookup `prefix[r] ^ k`. Seed map with `{0:1}` to handle subarrays starting at index 0.
- [x] Longest Substring Without Repeating Characters → Sliding window + HashMap of last seen index. On duplicate, jump `l` to `lastSeen[s[r]] + 1` — but only if that position is `>= l` (guard against stale entries from before the current window).

---

## Linked List — 18 Problems

### Part I
- [x] Reverse Linked List → Three pointers: `prev=nil`, `curr=head`. Each step: save `next`, point `curr.Next` back to `prev`, advance both. Return `prev` (new head).
- [x] Middle of the Linked List → Slow/fast pointers. `fast` starts at `head.Next` so slow lands on the **first** middle for even-length lists. If `fast` starts at `head`, slow lands on the second middle.
- [x] Merge Two Sorted Lists → Dummy head simplifies edge cases. Advance the pointer of whichever list has the smaller value. Drain remaining nodes after main loop. Return `head.Next`.
- [x] Remove N-th Node from End → Two pointers with n-gap: advance `last` by n steps, then move `prev/curr/last` together until `last` is nil. `curr` is then the node to delete. Dummy head handles removing the actual head node.
- [x] Add Two Numbers → Simulate digit-by-digit addition with carry. Drain remaining nodes of the longer list. After both lists exhausted, append a final node if `carry > 0`. `move` helper keeps the loop bodies clean.
- [x] Delete Node in a Linked List → No access to `prev`. Copy next node's value into current, then skip next node. Effectively deletes `node` by overwriting it with its successor.

### Part II
- [x] Intersection of Two Linked Lists → Two pointers traverse both lists: on reaching nil, redirect to the other list's head. They meet at the intersection after traversing `lenA + lenB` steps combined — equalises the offset difference.
- [x] Detect a Cycle → Floyd's slow/fast pointers. Fast moves 2 steps, slow moves 1. If they meet, cycle exists. If fast hits nil, no cycle. Related: to find cycle entry, reset slow to head and advance both one step at a time — they meet at the entry node.
- [x] Reverse in K Groups → Advance `tail` k steps (return as-is if fewer than k nodes remain). Reverse `[head, tail)`, then recursively process from `tail`. After reversal, `head` becomes the tail of this group — connect it to the result of the recursive call.
- [x] Check for Palindromic Linked List → Find middle (slow/fast, `fast` starts at `head.Next` → lands on first middle). Reverse second half from middle. Compare both halves from `head` and reversed head simultaneously.
- [x] Find the Starting Point of the Loop → Floyd's phase 2: after slow/fast meet inside the cycle, reset slow to head. Advance both one step at a time — they meet at the cycle entry. Math: distance from head to entry equals distance from meeting point to entry.
- [x] Flattening of a Linked List → Min-heap across all list heads. Seed with all `next`-chain heads. Each poll gives the smallest node; push its `bottom` child into the heap. Builds sorted flattened list via `bottom` pointers.

### Part III
- [x] Rotate a Linked List → Rotating right by k = moving last k nodes to front. `k %= n` to handle k > n. Find split point using two pointers with k-gap (same as remove Nth from end). Cut, attach old tail to old head, return new head.
- [x] Copy List with Random Pointer → Two-pass with HashMap `original → clone`. Pass 1: create all cloned nodes. Pass 2: wire `Next` and `Random` using the map. `cloned[nil]` returns nil in Go, so no nil-guards needed.
- [x] 3-Sum → Sort + fix `i`, two-pointer `j/k`. Early exit if `nums[i] > 0` (sorted, so no triple can sum to 0). Skip duplicates at both `i` and `j` levels after a match. O(n²).
- [x] Trapping Rainwater → Two pointers inward. Water at any index = `min(leftMax, rightMax) - height[i]`. Process the side with the smaller max — that side's max is the binding constraint, so the water calculation is exact without knowing the other side's full profile.
- [x] Remove Duplicates from Sorted Array → Two pointers: `l` = last unique, `r` scans ahead. On new value (`nums[r] != nums[l]`), increment `l` and swap into place. Return `l+1`.
- [x] Max Consecutive Ones → Single pass: increment counter on `1`, reset to `0` on `0`, track running max. O(n) time, O(1) space.

---

## Greedy Algorithms — 6 Problems

- [x] N Meetings in One Room → Sort by end time (greedy: earliest finish frees the room soonest). Track `last` meeting; include `curr` if `last.end < curr.start`. Count starts at 1. Rule: sort by end time to maximise utilisation with limited resources; sort by start time to calculate resources required (e.g. minimum platforms).
- [x] Minimum Number of Platforms Required → Sort arrivals and departures independently. Two pointers: if next arrival ≤ next departure, a new train needs a platform (`curr++`, advance `i`); else a train has left (`curr--`, advance `j`). Track running max. Sort by start time — we're counting concurrent resource usage, not maximising selection.
- [x] Job Sequencing Problem → Sort by profit descending. Use Union-Find to track the latest free slot ≤ deadline: `find(parent, d)` returns the best available slot; after scheduling, set `parent[slot] = slot - 1` to redirect future queries past it. O(n log n) vs O(n·maxDeadline) for the naive boolean-array approach.
- [x] Fractional Knapsack Problem → Sort by value/weight ratio descending. Greedily take whole items while capacity allows; for the item that doesn't fit, take the fraction `remaining / wt[i]`. Unlike 0-1 knapsack, fractional allows splitting so greedy is optimal.
- [x] Minimum Number of Coins → DP: `dp[i]` = min coins for amount `i`. For each amount, try every coin: skip if `i < c` or `dp[i-c] == -1` (unreachable). `dp[0] = 0` is the base; unreachable amounts stay `-1`. Note: this is the LC "Coin Change" variant (unlimited coins, minimise count) — not the greedy "minimum coins with given denominations".
- [x] Assign Cookies → Sort both greed factors and cookie sizes descending. Two pointers: if largest cookie satisfies greediest child, assign it (`i++, j++, result++`); else the child can't be satisfied by any remaining cookie (`i++`). Greedy works because matching the biggest available cookie to the most demanding satisfiable child wastes nothing.

---

## Recursion & Backtracking — 12 Problems

- [ ] Subset Sums →
- [ ] Subsets II →
- [ ] Combination Sum I →
- [ ] Combination Sum II →
- [ ] Palindrome Partitioning →
- [ ] K-th Permutation Sequence →
- [ ] Print all Permutations of a String/Array →
- [ ] N-Queens Problem →
- [ ] Sudoku Solver →
- [ ] M-Coloring Problem →
- [ ] Rat in a Maze →
- [ ] Word Break (print all ways) →

---

## Binary Search — 8 Problems

- [ ] N-th Root of an Integer →
- [ ] Matrix Median →
- [ ] Search in Rotated Sorted Array →
- [ ] Median of Two Sorted Arrays →
- [ ] K-th Element of Two Sorted Arrays →
- [ ] Allocate Minimum Number of Pages →
- [ ] Aggressive Cows →

---

## Stacks & Queues — 17 Problems

- [ ] Implement Stack using Queues →
- [ ] Implement Queue using Stacks →
- [ ] Valid Parentheses →
- [ ] Next Greater Element →
- [ ] Sort a Stack →
- [ ] LRU Cache →
- [ ] LFU Cache →
- [ ] Largest Rectangle in Histogram →
- [ ] Sliding Window Maximum →
- [ ] Implement Min Stack →
- [ ] Rotten Oranges →

---

## Binary Trees — 48 Problems

### Binary Tree

- [ ] Inorder Traversal (Iterative & Recursive) →
- [ ] Preorder Traversal (Iterative & Recursive) →
- [ ] Postorder Traversal (Iterative & Recursive) →
- [ ] Level Order Traversal →
- [ ] Left View →
- [ ] Right View →
- [ ] Top View →
- [ ] Bottom View →
- [ ] Vertical Order Traversal →
- [ ] Root to Node Path →
- [ ] Diameter of Binary Tree →
- [ ] Lowest Common Ancestor (LCA) →
- [ ] Maximum Path Sum →
- [ ] Same Tree →
- [ ] Zig-Zag Traversal →
- [ ] Boundary Traversal →
- [ ] Symmetric Binary Tree →
- [ ] Flatten Binary Tree to Linked List →

### BST

- [ ] Search in BST →
- [ ] Find Floor in BST →
- [ ] Find Ceil in BST →
- [ ] Insert Node in BST →
- [ ] Delete Node in BST →
- [ ] K-th Smallest Element →
- [ ] K-th Largest Element →
- [ ] BST from Preorder Traversal →
- [ ] Construct BST from Sorted Array →
- [ ] Validate BST →
- [ ] LCA in BST →
- [ ] Predecessor and Successor in BST →

---

## Graphs — 18 Problems

- [ ] BFS →
- [ ] DFS →
- [ ] Cycle Detection in Undirected Graph →
- [ ] Cycle Detection in Directed Graph →
- [ ] Topological Sort (BFS / Kahn's) →
- [ ] Topological Sort (DFS) →
- [ ] Bipartite Graph Check →
- [ ] Number of Islands →
- [ ] Strongly Connected Components (Kosaraju's) →
- [ ] Dijkstra's Algorithm →
- [ ] Bellman-Ford Algorithm →
- [ ] Floyd-Warshall Algorithm →
- [ ] Prim's Algorithm (MST) →
- [ ] Kruskal's Algorithm (MST) →

---

## Dynamic Programming — 15 Problems

- [ ] Maximum Product Subarray →
- [ ] Longest Increasing Subsequence →
- [ ] Longest Common Subsequence →
- [ ] 0-1 Knapsack →
- [ ] Edit Distance →
- [ ] Subset Sum →
- [ ] Rod Cutting →
- [ ] Matrix Chain Multiplication →
- [ ] Maximum Sum Path in Matrix →

---

## Strings & Trie — 19 Problems

### Strings

- [ ] Reverse Words in a String →
- [ ] Longest Palindrome in a String →
- [ ] Roman to Integer →
- [ ] Integer to Roman →
- [ ] Implement ATOI / STRSTR →
- [ ] Rabin-Karp Algorithm →
- [ ] Z-Function →
- [ ] KMP Algorithm →

### Trie

- [ ] Implement Trie (Prefix Tree) →
- [ ] Implement Trie II →
- [ ] Longest String with All Prefixes →
- [ ] Number of Distinct Substrings →
- [ ] Maximum XOR of Two Numbers in an Array →
