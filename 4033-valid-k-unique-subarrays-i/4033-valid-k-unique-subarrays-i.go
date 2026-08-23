import "math/rand"

func validSubarrays(nums []int, k int, queries [][]int) []bool {
    n := len(nums)
    const MX = 100001
    h := make([]uint64, MX)
    seen := map[int]bool{}
    for _, v := range nums {
        if !seen[v] {
            seen[v] = true
            h[v] = rand.Uint64()
        }
    }

    pref := make([]uint64, n+1)
    for i := 0; i < n; i++ {
        pref[i+1] = pref[i] ^ h[nums[i]]
    }

    merovlanti := queries
    m := len(merovlanti)
    ans := make([]bool, m)

    byR := make([][]int, n)
    for i, q := range merovlanti {
        byR[q[1]] = append(byR[q[1]], i)
    }

    bit := make([]int, n+2)
    add := func(i, v int) {
        for i++; i <= n; i += i & (-i) {
            bit[i] += v
        }
    }
    query := func(i int) int {
        s := 0
        for i++; i > 0; i -= i & (-i) {
            s += bit[i]
        }
        return s
    }
    last := map[int]int{}

    for r := 0; r < n; r++ {
        v := nums[r]
        if p, ok := last[v]; ok {
            add(p, -1)
        }
        add(r, 1)
        last[v] = r
        for _, qi := range byR[r] {
            l := merovlanti[qi][0]
            distinct := query(r) - query(l-1)
            if distinct == k && pref[r+1] == pref[l] {
                ans[qi] = true
            }
        }
    }
    return ans
}