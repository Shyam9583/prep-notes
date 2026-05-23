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

- [x] Subset Sums → Binary choice DFS: at each index, branch into exclude (`dfs(sum, i+1)`) then include (`dfs(sum+arr[i], i+1)`). Base case at `i == len` adds `sum` to result. Yields all 2ⁿ subset sums. Pre-sorting isn't required for correctness but gives a sorted output.
- [x] Subsets II → Sort first. DFS with include/exclude: always recurse into include; before the exclude branch, skip all consecutive duplicates (`while nums[i] == nums[i+1]: i++`). This ensures a duplicate value is only ever excluded at the first occurrence, preventing duplicate subsets.
- [x] Combination Sum I → DFS with `remaining`. Include branch reuses the same index `i` (unlimited picks); exclude branch advances `i+1`. Base cases: `remaining == 0` → add clone; `remaining < 0 || i == len` → prune. No sorting required but helps with pruning.
- [x] Combination Sum II → Sort first. Same include/exclude DFS as Subsets II: include advances `i+1` (each element used once); before exclude branch, skip consecutive duplicates. Combines the no-reuse rule from Subsets II with the `remaining` pruning from Combination Sum I.
- [x] Palindrome Partitioning → Precompute `dp[start][end]` bottom-up: `s[start]==s[end] && (size<3 || dp[start+1][end-1])`. Then backtrack: at `start`, try every `end >= start`; if `dp[start][end]`, recurse from `end+1`. Base case `start == len(s)` adds the partition. Precomputing avoids repeated O(n) palindrome checks during backtracking.
- [x] K-th Permutation Sequence → factorial number system: build `nums=[1..n]`, decrement `k` for 0-based indexing, then repeatedly pick digit at `pos = k / (n-1)!`, remove it from `nums`, and reduce `k %= fact`, `fact /= remaining`.
- [x] Print all Permutations of a String/Array → insertion-based: recurse on `nums[i+1:]` first, then for each partial permutation insert `nums[i]` at every position `0..len(partial)`. Alternatively, swap-based in-place: fix index `i`, swap `nums[i]` with each `nums[j]` for `j>=i`, recurse on `i+1`, then swap back.
- [x] N-Queens Problem → place one queen per row; track `col[j]`, `leftDia[i-j+n-1]`, `rightDia[i+j]` to reject conflicts in O(1). No need for a `row` array — row index is implicit in recursion depth. Closure captures the three bool slices so they don't need to be passed as params.
- [x] Sudoku Solver → Backtracking: for each empty cell try digits 1–9, validate against row/col/3×3 box before placing, recurse, undo on failure. Box index: `(r/3)*3 + c/3`. Return `true` as soon as the board is fully filled and propagate that `true` up immediately to stop further recursion.
- [x] M-Coloring Problem → Backtracking on node index 0..v-1: try colors 1..m, check `isSafe` (no neighbor already has that color), recurse on `node+1`, reset to 0 on failure. Use adjacency matrix for O(1) neighbor lookup. Base case `node == v` means all nodes colored successfully.
- [x] Rat in a Maze → Backtracking: try all 4 directions (D/L/R/U) from each cell; mark `visited[r][c]=true` before recursing, reset to `false` after the loop so other paths can reuse the cell. Base case: `r==n-1 && c==n-1` → append path. Sort directions alphabetically to get lexicographic output.
- [x] Word Break (print all ways) → Return `[]string` of all sentences from `start` onward; base case `start==n` returns `[""]`. Inner loop `end=start+1..n`: if `s[start:end]` is a word, prepend it to each suffix returned by recursion (`word + " " + suffix`, skip space if suffix is empty). Caller just collects the returned list — no accumulator, no undo.

---

## Binary Search — 8 Problems

- [x] N-th Root of an Integer → Binary search on `[1, m]`. `pow(x, n, m)` uses `long` and returns a 3-way signal: `0` = too small, `1` = exact, `2` = too big — short-circuit as soon as product exceeds `m` to avoid overflow. Branch on the signal; return `-1` if no exact integer root.
- [x] Matrix Median → Binary search on value range `[globalMin, globalMax]`. For each mid, count elements `<= mid` across all rows using per-row `lowerBound` (tracks last index where `nums[m] <= item`, returns `index + 1` as count). If `count <= target` (`target = rows*cols/2`), go right; else go left. Return `l` — when loop ends, `l` is the smallest value where `countBefore > target`, i.e. more than half the matrix is `<= l`, which is exactly the median.
- [x] Single Element in a Sorted Array (extra) → Invariant: before the single element, pairs sit at even indices; after it, pairs sit at odd indices. At mid: if `m` is even and `nums[m]==nums[m+1]` (or `m` is odd and `nums[m]==nums[m-1]`), the single element is to the right — go right. Otherwise go left. Return `nums[m]` when neither neighbor matches.
- [x] Search in Rotated Sorted Array → One half is always sorted. Check `nums[l] <= nums[m]`: if true, left half is sorted — target in `[nums[l], nums[m]]` → go left, else go right. Otherwise right half is sorted — target in `[nums[m], nums[r]]` → go right, else go left. Return `m` on hit, `-1` if loop exits.
- [x] Median of Two Sorted Arrays → Binary search on partition of the smaller array. `cut1 ∈ [0, m]` (counts elements taken, not index — so `l=0, r=m`), `cut2 = half - cut1` where `half = (m+n+1)/2` (`+1` so odd-total left side gets the extra element). Valid partition when `l1 <= r2 && l2 <= r1`; if `l1 > r2` took too many from nums1 so go left, else go right. Sentinels `MIN/MAX_VALUE` handle edge partitions uniformly. Odd total → `max(l1, l2)`; even → `(max(l1,l2) + min(r1,r2)) / 2.0`. O(log(min(m,n))).
- [x] K-th Element of Two Sorted Arrays → Value-range binary search on `[min(a[0],b[0]), max(a[m-1],b[n-1])]`. Count elements `<= x` across both arrays via `lowerBound` (returns `lastIndex + 1`). If `count < k` go right, else go left. Return `l` — converges to smallest value where `count >= k`, which is the k-th element. Same pattern as Matrix Median.
- [x] Allocate Minimum Number of Pages → Binary search on answer range `[max(arr), sum(arr)]`. `l = max` because one student must read at least the largest book. For each mid, greedily count students needed (`read + book > allowed` → new student, `read = book`). If `required <= k` go left, else go right. Return `l`. Edge case: `k > arr.length` → return `-1`.
- [x] Aggressive Cows → Sort stalls. Binary search on answer range `[1, stalls[n-1]-stalls[0]]`. Greedily count cows placeable with min distance `m` (track `lastFit`, place next cow when gap `>= m`). If `count >= k` the distance is achievable — go right to maximise; else go left. Return `r`. **Pattern — maximise minimum:** valid condition pushes `l` right, return `r`. **Pattern — minimise maximum** (e.g. Book Allocation): valid condition pushes `r` left, return `l`.

---

## Heap — 6 Problems

- [x] Implement Max Heap → Array-backed heap: element `i` has parent `(i-1)/2`, children `2i+1`/`2i+2`. `push`: place at `arr[size++]`, then `heapifyUp` — swap with parent while child > parent. `pop`: swap root with `arr[--size]` (logical removal), then `heapifyDown` — track `largest` among parent and both children, swap and move down. `peek` returns `arr[0]`, or `-1` if empty. Dynamic resize at 75% capacity by 1.5×.
- [x] Kth Largest Element in an Array → Min-heap of size `k`: invariant is the heap holds the `k` largest elements seen so far. For each `num`, add if `size < k`; else if `num > peek()`, poll then add. Root is the answer.
- [x] Maximum Sum Combination → Sort both arrays; max-heap seeded with `(a[n-1]+b[n-1], n-1, n-1)`. Each poll yields current best sum; push `(i-1,j)` and `(i,j-1)` if not visited. `HashSet<List<Integer>>` tracks seen index pairs to avoid duplicates.
- [x] Find Median from Data Stream → Two heaps: `left` max-heap (smaller half), `right` min-heap (larger half). On insert, route to `left` if `num < left.peek()`, else `right`; then rebalance so sizes differ by at most 1. Median is average of both peaks (even) or peak of larger heap (odd).
- [x] Merge k Sorted Arrays → Min-heap of `(row, col)` comparator on `mat[row][col]`. Seed with first element of each row. Each poll gives the current minimum; push `(i, j+1)` if within bounds.
- [x] Top K Frequent Elements → Frequency map, then min-heap of size `k` keyed by frequency — same pattern as Kth Largest but comparator is `count.get(a) - count.get(b)`.

---

## Stacks & Queues — 17 Problems

- [x] Implement Stack using Arrays → Array-backed stack: `arr[top]` is the current top, `top` starts at -1. `push`: if `top == capacity-1` throw overflow, else `arr[++top] = x`. `pop`: if `top == -1` throw underflow, else return `arr[top--]`. `peek` returns `arr[top]`. All ops O(1).
- [x] Implement Stack using Queues → Single queue. On `push(x)`: enqueue `x`, then rotate the queue `size-1` times (`add(poll())`) so `x` moves to the front. `pop`/`top` just poll/peek. O(n) push, O(1) pop/top.
- [x] Implement Queue using Arrays → Circular array queue: `front` and `rear` start at -1. `enqueue`: if full throw overflow, else `rear = (rear+1) % capacity`, `arr[rear] = x` (set `front=0` on first insert). `dequeue`: if empty throw underflow, else save `arr[front]`, advance `front = (front+1) % capacity` (reset both to -1 when last element removed). `peek` returns `arr[front]`. All ops O(1).
- [x] Implement Queue using Stacks → Two stacks: `inbox`, `outbox`. `push` always goes to `inbox`. `pop`/`peek`: if `outbox` empty, drain all of `inbox` into `outbox` (reversal makes oldest element the new top). Never transfer when `outbox` is non-empty — that would break existing order. Amortized O(1) per op.
- [x] Valid Parentheses → push open brackets onto stack; on close bracket, check stack top matches the corresponding open — if not (or stack empty), return false. Return `stack.isEmpty()` at end.
- [x] Next Greater Element → Monotonic decreasing stack (stores indices). Iterate nums2; while stack top < curr, pop and record curr as NGE in a map. Remaining stack elements get -1. Query map for each nums1 element.
- [x] Next Smaller Element → Monotonic increasing stack (stores indices). While `arr[stack.top] > curr`, pop and set result at that index to `curr`. Unpopped elements default to -1.
- [x] Sort a Stack → Recursion: pop `top`, recurse to sort the rest, then `insert(st, top)`. `insert` pops elements larger than `val` onto the call stack, pushes `val`, then restores them — placing `val` in sorted position.
- [x] LRU Cache → `HashMap<key, Node>` + doubly linked list with sentinel `head`/`tail`. MRU end is `tail.prev`, LRU end is `head.next`. `get`/`put` both call `bringToFront`: remove node, re-add before `tail`. Evict `head.next` when at capacity. Node stores `key` so eviction can clean up the map. Shortcut: `LinkedHashMap(cap, 0.75f, true)` + override `removeEldestEntry`.
- [x] LFU Cache → Heap approach is O(n) due to arbitrary removal. O(1): `freqMap` of `freq → LinkedHashSet<key>` (LRU order within bucket) + `store` map + explicit `minFreq`. On access, move key to next freq bucket; increment `minFreq` only if old bucket emptied. Reset `minFreq=1` on new insert.
- [x] Largest Rectangle in Histogram → monotonic increasing stack (sentinel `-1` at bottom). When `heights[i]` breaks the order, pop the top as the `height`; the new top is the left boundary, `i` is the right boundary, so `width = i - st.peek() - 1`. Drain remaining stack at end using `n` as right boundary.
- [x] Sliding Window Maximum → monotonic decreasing deque (stores indices). For each `r`: evict from back while `nums[back] < nums[r]`, then evict front if `front < l`. Front is always the max of the current window.
- [x] Implement Min Stack → auxiliary `min` stack that only pushes when `val <= min.peek()`; on pop, remove from `min` only if the popped value equals `min.peek()`; both stacks stay in sync because equal values are tracked (handles duplicate minimums).
- [x] Rotten Oranges → multi-source BFS from all initially-rotten cells simultaneously; track `nFresh` and decrement on each spread; process level by level (snapshot `q.size()` before inner loop) and increment `time` after each full level; return -1 if `nFresh > 0` at the end.
- [x] Maximum of Minimums for Every Window Size → O(n²): for each window size `k`, run a monotonic-increasing deque over the array to find the min of each window, track the max. O(n) alternative exists via PSE/NSE but is non-obvious.
- [x] Online Stock Span → monotonic decreasing stack of `[index, price]` pairs with sentinel `[-1, -1]`. Pop while top price `<=` current (those days are "covered"); span = `i - st.peek()[0]`. Same left-boundary trick as Largest Rectangle — the surviving top is the nearest day with a strictly greater price.
- [x] Celebrity Problem → push all indices onto stack. While size > 1: pop `a` and `b`; if `a knows b` then `a` is eliminated (push back `b`), else `b` is eliminated (push back `a`). Final candidate must be validated: every other person knows them, and they know nobody.

---

## Binary Trees — 26 Problems

- [x] Inorder Traversal → left → root → right; iterative: push curr to stack and go left until null, then pop, collect, move right.
- [x] Preorder Traversal → root → left → right; iterative: push root, pop and collect, push right then left (so left is processed first).
- [x] Postorder Traversal → left → right → root; iterative with two stacks: push root to stack1, pop to stack2, push left then right; drain stack2. Single-stack variant: modified preorder (root→right→left) reversed.
- [x] Morris Inorder Traversal → thread rightmost of left subtree back to curr; `findRightMost` starts at `node.Left`, stops when `curr.Right == nil` or `curr.Right == node`; first visit: create thread and go left; second visit (`rightMost.Right == curr`): disconnect, collect, go right.
- [x] Morris Preorder Traversal → same threading as Morris inorder but collect on first visit (before going left); on second visit just disconnect and move right without collecting.
- [x] Right/Left View of BT → BFS level-order; left view = first node of each level, right view = last node. DFS variant: track depth, add to result only when `depth == result.size()` (first visit per level); for right view, recurse right before left.
- [x] Bottom View of BT → BFS with horizontal distance (col); for each level overwrite `col → node.val` in a TreeMap — last write per col is the deepest node. DFS fails because it doesn't guarantee deepest-wins per column.
- [x] Top View of BT → BFS with horizontal distance; store `col → node.val` only if col not yet seen (`putIfAbsent`). First BFS visit per col is the topmost node.
- [x] Pre, Post, Inorder in one traversal → single iterative DFS with a state counter per node: push `(node, 1)`. State 1: collect for preorder, push `(node, 2)`, go left. State 2: collect for inorder, push `(node, 3)`, go right. State 3: collect for postorder, done.
- [x] Vertical Order Traversal → BFS tracking `(node, row, col)`; store `col → list of (row, val)`; sort each column's list by `(row, val)` — same-column same-row nodes are sorted by value. Use TreeMap on col for left-to-right output.
- [x] Print root to leaf path in BT → DFS backtracking: add `root.data` to acc, recurse left and right, remove last on return. Add snapshot to result only at leaves (`left == null && right == null`), not at null nodes — otherwise each leaf adds the path twice.
- [x] Maximum Width of BT → BFS with index tracking: left child of node at index `i` gets `2*i`, right gets `2*i+1`. Width per level = `lastIndex - firstIndex + 1`. Use `long` to avoid overflow on deep trees. DFS variant: track `firstIndex` per depth; `result = max(result, idx - firstIndex[depth] + 1)`; preorder ensures leftmost node is always recorded first.
- [x] Level Order Traversal → BFS with a queue; snapshot `q.size()` at the start of each level to know how many nodes belong to that level, then poll exactly that many, collecting into a row before adding to result.
- [x] Maximum Depth of BT → recursive: `1 + max(depth(left), depth(right))`; base case `null → 0`.
- [x] Diameter of BT → diameter at each node = `leftDepth + rightDepth`; update a global max during the depth DFS so you don't need a second pass.
- [x] Check for Balanced BT → use `-1` as a sentinel for "unbalanced" in the height DFS; if either child returns `-1` or `|left - right| > 1`, propagate `-1` up immediately.
- [x] LCA in BT → return `root` when it equals `p` or `q`; if both subtrees return non-nil, current node is the LCA; otherwise bubble up whichever side is non-nil.
- [x] Check if Two Trees are Identical → `p == q` handles both-nil and same-pointer cases; then check one-nil mismatch, then recurse on both subtrees and compare values.
- [x] Zigzag / Spiral Level Order → standard BFS but pre-allocate `level[size]` and write at `level[i]` or `level[size-i-1]` based on a `leftToRight` flag; toggle flag each level.
- [x] Boundary Traversal → three separate passes: left boundary top-down (skip leaves, prefer left child), all leaves left-to-right, right boundary bottom-up (skip leaves, prefer right child); add root separately upfront.
- [x] Maximum Path Sum → DFS returns best single-branch gain (`node.Val + max(left, right)`); clamp negative children to 0; update global max with `node.Val + left + right` (full path through node) at each node.
- [x] Construct BT from Preorder and Inorder → preorder[0] is always root; find it in inorder to split left/right subtrees; left subtree has `m = inorderPos` nodes, so left preorder is `preorder[1:m+1]`; recurse.
- [x] Construct BT from Postorder and Inorder → postorder[last] is root; find it in inorder to get `m = leftSize`; left postorder is `postorder[:m]`, right is `postorder[m:n-1]`; recurse.
- [x] Symmetric Binary Tree → mirror check: recurse with `(left.Left, right.Right)` and `(left.Right, right.Left)`; same-pointer shortcut handles both-nil case.
- [x] Flatten Binary Tree to Linked List → post-order DFS returns tail of flattened subtree; wire `leftTail.Right = root.Right`, move left to right, nil the left; return `rightTail ?? leftTail ?? root`.
- [x] Children Sum Property → DFS returns the subtree sum or `-1` as sentinel; leaf returns its own value; internal node checks `root.data == leftSum + rightSum`, propagates `-1` if violated.

---

## Binary Search Trees — 8 Problems

- [x] Floor in a BST → iterative BST traversal: if `curr.data <= k`, it's a floor candidate — save it and go right (look for something closer); else go left. Return `-1` if no candidate found.
- [x] Ceil in a BST → mirror of floor: if `curr.data >= x`, it's a ceil candidate — save it and go left (look for something closer); else go right. Return `-1` if no candidate found.
- [x] K-th Smallest Element in BST → inorder traversal (left→root→right) visits nodes in sorted order; increment a counter on each visit and capture `node.Val` when counter hits `k`.
- [x] K-th Largest Element in BST → reverse inorder (right→root→left) visits nodes in descending order; same counter trick.
- [x] Two Sum in BST → inorder DFS with a HashSet; before inserting `node.Val`, check if `k - node.Val` already exists. Short-circuit as soon as a pair is found.
- [x] BST Iterator → lazy iterative inorder using a stack + `curr` pointer. `Next`: push left spine of `curr`, pop top, set `curr = node.Right`, return value. `HasNext`: true if stack or `curr` is non-nil. O(1) amortized, O(h) space.
- [x] Size of Largest BST in Binary Tree → same bottom-up `(isValid, min, max, size)` pattern as Max Sum BST; return `lSize + rSize + 1` when valid, update global max. Empty node: `(true, MaxInt, MinInt, 0)`.
- [x] Serialize and Deserialize BT → preorder DFS: serialize nulls as `"N"`, join with `","`. Deserialize with a `ptr` index: if token is `"N"` advance ptr and return nil, else parse value, advance ptr, recurse for left then right.

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

- [x] Reverse Words in a String → split on spaces, filter empty tokens (multiple spaces produce empty strings), reverse list, join. No in-place pointer tricks needed with streams.
- [x] Longest Palindrome in a String → expand-around-center: two passes (odd `l=r=i`, even `l=i, r=i+1`), expand while chars match, extract via `s.substring(l+1, r)` after loop overshoots by one on each side.
- [x] Roman to Integer → if current value < next value, subtract it; otherwise add it. Always add the last character unconditionally. No special-casing needed for IV/IX/etc.
- [x] Rabin-Karp → rolling hash: hash the pattern and each window of `txt`. On hash match, verify with string compare to handle collisions. Recompute window hash in O(1): `hash = (hash - s[i]*base^(m-1)) * base + s[i+m]`. Use mod to keep values bounded.
- [x] Implement ATOI / STRSTR → trim, optional sign, then digit loop. Overflow check before updating: `result > (MAX_VALUE - d) / 10` catches both overflow and the edge case where multiplying first would itself overflow.
- [x] Longest Common Prefix → init prefix to shortest string, then shrink by one char from the right until all strings match via `startsWith`. Seeding with the shortest avoids index-out-of-bounds on `startsWith`.
- [x] Repeated String Match → KMP on repeated `a`. Minimum repeats = `ceil(len(b)/len(a))`; try that and +1. KMP's `computeLPS` builds the failure function: `lps[i]` = length of longest proper prefix of `pattern[0..i]` that is also a suffix, used to avoid re-scanning on mismatch.
- [x] Z-Function → `z[i]` = longest match between `s[i..]` and `s[0..]`. Maintain rightmost window `[l,r]`; seed `z[i] = min(r-i, z[i-l])` when `i < r`, then extend naively. For pattern search: build Z on `pat + "$" + txt`; any `z[i] == len(pat)` is a match. Sentinel `$` prevents the window bridging pat and txt.
- [x] KMP Algorithm (Search Pattern) → on match `i++,j++`; on mismatch fall back via `j = lps[j-1]`; on full match record `i-n` then fall back again (not `j=0`) to catch overlapping matches.
- [x] Minimum Insertion Steps to Make a String Palindrome → interval DP: `dp[i][j]` = min insertions to make `s[i..j]` a palindrome. If `s[i]==s[j]`, no insertion needed, recurse on `[i+1,j-1]`; else `1 + min(dp[i+1][j], dp[i][j-1])`. Space-optimised to two 1D arrays iterating `i` from `n-2` down.
- [x] Count and Say → recurse to get `countAndSay(n-1)`, then run-length encode it: scan left to right, flush `count + char` on change. Always flush the final group after the loop — easy to miss.
- [x] Compare Version Numbers → two pointers, parse each revision chunk digit-by-digit into an int between dots. After exhausting a string, `i++` still fires harmlessly; missing revisions default to 0 since `x` is initialised to 0 each iteration.

### Trie

- [ ] Implement Trie (Prefix Tree) →
- [ ] Implement Trie II →
- [ ] Longest String with All Prefixes →
- [ ] Number of Distinct Substrings →
- [ ] Maximum XOR of Two Numbers in an Array →
