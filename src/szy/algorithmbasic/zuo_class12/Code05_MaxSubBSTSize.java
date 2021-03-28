package szy.algorithmbasic.zuo_class12;


import java.util.ArrayList;

public class Code05_MaxSubBSTSize {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
	}

	public static int maxSubBSTSize2(Node head) {
		if(head == null) {
			return 0;
		}
		return process(head).maxBSTSubtreeSize;
	}

	public static class Info {
		public int maxBSTSubtreeSize;
		public int allSize;
		public int max;
		public int min;

		public Info(int m, int a, int ma, int mi) {
			maxBSTSubtreeSize = m;
			allSize = a;
			max = ma;
			min = mi;
		}
	}

	public static Info process(Node x) {
		if (x == null) {
			return null;
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int max = x.value;
		int min = x.value;
		int allSize = 1;
		if (leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			allSize += leftInfo.allSize;
		}
		if (rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			allSize += rightInfo.allSize;
		}
		int p1 = -1;
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubtreeSize;
		}
		int p2 = -1;
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubtreeSize;
		}
		int p3 = -1;
		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
		if (leftBST && rightBST) {
			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
			boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
			if (leftMaxLessX && rightMinMoreX) {
				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
				p3 = leftSize + rightSize + 1;
			}
		}
		return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}



	public static class Info1 {
		int maxBSTSubSize;//当前节点最大二叉搜索子树的大小
		int max;//最大值
		int min;//最小值
		int size;//节点大小

		public Info1(int maxBSTSubSize, int max, int min, int size) {
			this.maxBSTSubSize = maxBSTSubSize;
			this.max = max;
			this.min = min;
			this.size = size;
		}
	}

	public static int maxSubBSTSize11(Node head) {
		if (head == null) return 0;
		return process1(head).maxBSTSubSize;

	}

	public static Info1 process1(Node node) {

		if (node == null) return null;
		Info1 leftInfo = process1(node.left);
		Info1 rightInfo = process1(node.right);
		int maxBSTSubSize = -1;
		int max = node.value;
		int min = node.value;
		int size = 1;
		if (leftInfo != null) {
			max = Math.max(max, leftInfo.max);
			min = Math.min(min, leftInfo.min);
			size += leftInfo.size;
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
			min = Math.min(min, rightInfo.min);
			size += rightInfo.size;
		}
		boolean isleftBST = leftInfo == null || leftInfo.maxBSTSubSize == leftInfo.size;
		boolean isrightBST = rightInfo == null || rightInfo.maxBSTSubSize == rightInfo.size;
		int p1 = 0;
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubSize;
		}
		int p2 = 0;
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubSize;
		}
		int p3 = 0;
		//如果左右子树都满足二叉搜索树
		if (isleftBST && isrightBST) {

			if (leftInfo == null && rightInfo == null) {
				p3 = 1;
			} else if (leftInfo == null) {
				if (rightInfo.min > node.value) {
					p3 = rightInfo.maxBSTSubSize + 1;
				}
			} else if (rightInfo == null) {
				if (leftInfo.max < node.value) {
					p3 = leftInfo.maxBSTSubSize + 1;
				}
			} else {
				if (leftInfo.max < node.value && rightInfo.min > node.value) {
					p3 = rightInfo.maxBSTSubSize + leftInfo.maxBSTSubSize + 1;
				}
			}
		}
		maxBSTSubSize = Math.max(Math.max(p1, p2), p3);
		return new Info1(maxBSTSubSize, max, min, size);
	}
	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTSize1(head) != maxSubBSTSize11(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
