#define REP(i, n) for (i = 0; i < n; i++)
#define pb(a) push_back(a)
#define vi vector<long>
#define ll long long
#include <bits/stdc++.h>
using namespace std;
struct node
{
    ll value;
    node *left;
    node *right;
};

node *createNode(ll value)
{
    node *t = new node();
    t->value = value;
    t->right = t->left = NULL;
    return t;
}

void deleteNode(node *t)
{
    delete t;
}


node *replaceNegativeOne(node *root)
{
    if (root == NULL || (root->value == -1 && root->left == NULL && root->right == NULL))
        return NULL;
    root->left = replaceNegativeOne(root->left);
    root->right = replaceNegativeOne(root->right);
    return root;
}

node *createTreeByLevelTree()
{
    ll n, m;
    queue<node *> q;

    node *root, *t;

    root = NULL;

    while (cin >> n)
    {
        if (q.empty())
        {
            root = createNode(n);
            q.push(root);
            continue;
        }
        cin >> m;

        t = q.front();
        q.pop();

        t->left = createNode(n);
        t->right = createNode(m);

        if (t->left->value != -1)
        {
            q.push(t->left);
        }

        if (t->right->value != -1)
        {
            q.push(t->right);
        }
          if (q.empty())
        {
            break;
        }
    }

    return root;
}

void deleteTree(node *node)
{
    if (node == NULL)
        return;

    deleteTree(node->left);
    deleteTree(node->right);
    delete node;
}

// Complete the findTurnCount function below.
/* For your reference:
struct node
{
    long long value;
    node *left;
    node *right;
};
*/
void findMaxBendsUtil(node* node,
                      char dir, int bends,
                      int* maxBends, int soFar,
                      int* len)
{
    // Base Case
    if (node == NULL)
        return;
    // Leaf node
    if (node->left == NULL && node->right == NULL) {
        if (bends > *maxBends) {
            *maxBends = bends;
            *len = soFar;
        }
    }
        // Recurring for both left and right child
    else {
        if (dir == 'l') {
            findMaxBendsUtil(node->left, dir,
                             bends, maxBends,
                             soFar + 1, len);
            findMaxBendsUtil(node->right, 'r',
                             bends + 1, maxBends,
                             soFar + 1, len);
        }
        else {
            findMaxBendsUtil(node->right, dir,
                             bends, maxBends,
                             soFar + 1, len);
            findMaxBendsUtil(node->left, 'l',
                             bends + 1, maxBends,
                             soFar + 1, len);
        }
    }
}
int findTurnCount(node* node)
{
    //write your code here
     if (node == NULL)
        return 0;
    int len = 0, bends = 0, maxBends = -1;
    // Call for left subtree of the root
    if (node->left)
        findMaxBendsUtil(node->left, 'l',
                         bends, &maxBends, 1, &len);
    // Call for right subtree of the root
    if (node->right)
        findMaxBendsUtil(node->right, 'r', bends,
                         &maxBends, 1, &len);
    // Include the root node as well in the path length
    len++;
    return maxBends;
}


int main()
{
    node *root = NULL;
    root = createTreeByLevelTree();
    root = replaceNegativeOne(root);
    cout<<findTurnCount(root)<<endl;
    deleteTree(root);
    return 0;
}
