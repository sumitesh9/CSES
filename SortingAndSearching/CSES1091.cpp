#include <iostream>
#include <set>
#include <vector>

using namespace std;

/*
    Insert keys in multiset and binary search to find
    greatest ticket cost less than equal to current
    ticket cost and then decrement its frequency , if
    no such ticket exist answer is -1

    Submit PR if any one has Java AC submission.
*/

int main() {
    int n, m, x;
    cin >> n >> m;
    multiset<int, greater<int>> mset;
    while (n > 0) {
        cin >> x;
        mset.insert(x);
        --n;
    }

    while (m > 0) {
        cin >> x;
        auto itr = mset.lower_bound(x);
        if (itr == mset.end()) cout << "-1\n";
        else {
            cout << *itr << endl;
            mset.erase(itr);
        }
        --m;
    }
}