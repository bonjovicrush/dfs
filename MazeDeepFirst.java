package test1230;

import java.util.LinkedList;

public class MazeDeepFirst {

	static int dir = 8;
	static Offset[] move = new Offset[dir];
	static LinkedList<Offset> path = new LinkedList<Offset>();

	public static int deepFirstSearch(int dMaze[][], Offset dPos) {
		Offset dOffset = new Offset(0, 0);
		int dMoveIndex = -1;
		for (int i = 0; i < dir; i++) {
			if (dMaze[dPos.row + move[i].row][dPos.col + move[i].col] == 0) {
				dOffset.col = dPos.col;
				dOffset.row = dPos.row;
				dOffset.direction = i;
				path.add(dOffset);
				dMaze[dPos.row][dPos.col] = 1;
				dMoveIndex = i;
				break;
			}
		}
		return dMoveIndex;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Offset start = new Offset(1, 1);
		Offset nowPos = new Offset(1, 1); 
		Offset dest = new Offset(3, 3);
		Offset result = new Offset(1, 1);

		boolean wayOut = true;

		int nextPos;

		int mazeRowSize = 10;	//�̷��� ũ��
		int mazeColSize = 10;

		//8���� ������
		move[0] = new Offset(0, -1);	
		move[1] = new Offset(1, -1);
		move[2] = new Offset(1, 0);
		move[3] = new Offset(1, 1);
		move[4] = new Offset(0, 1);
		move[5] = new Offset(-1, 1);
		move[6] = new Offset(-1, 0);
		move[7] = new Offset(-1, -1);

		int[][] maze = new int[mazeRowSize][mazeColSize];	//������ �̷� �����ϱ� ���� 2���� �迭
		int[][] mark = new int[mazeRowSize][mazeColSize];	//Ž���� ���� ��ũ�ϱ� ���� 2���� �迭
		dest.row = mazeRowSize - 2;	//���� ��ġ ��ǥ ����
		dest.col = mazeColSize - 2;

		//������ �̷� ����
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
					maze[i][j] = 1;
				} else {
					maze[i][j] = (int) (Math.random() * 2);	//0�� ���� �ִ� ��, 1�� ��
				}
			}
		}
		maze[maze.length - 2][maze[0].length - 2] = 0;	//������ �ⱸ�� 0���� �����ִ±�� ����
		maze[1][1] = 0;
		// ������ �̷� ���
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}

		// ---------------------------------- �ʱ⼳�� �Ϸ�

		nowPos.col = start.col;
		nowPos.row = start.row; // ���۰��� ���� ��ġ�� ����

		while (nowPos.col != dest.col || nowPos.row != dest.row) {	//�ⱸ���� �����Ҷ����� �ݺ�
			
			nextPos = deepFirstSearch(maze, nowPos);	//���� ��� ã��
			//������ġ���� ���̻� ������ ������ ������ ��ΰ� ����. 
			if (nextPos == -1 && nowPos.row == start.row && nowPos.col == start.col) {
				wayOut = false;
				break;
			}
			//������ ������ ������ġ�� ������ �����ϰ� ������ġ�� ���ƿͼ� �ٸ���� �˻�
			if (nextPos == -1) {
				maze[nowPos.row][nowPos.col] = 1;
				nowPos = path.removeLast();
				wayOut = false;
			}

			// ���� ��� ����
			else {
				nowPos.col += move[nextPos].col;
				nowPos.row += move[nextPos].row;
			}
			wayOut = true;

		}

		if (wayOut == true) {
			// ������ �ⱸ�� Ǫ��
			path.add(nowPos);

			// ������ ��� ��ǥ ���
			System.out.println("deepFirstSearch ��� ��ǥ ");
			while (path.isEmpty() != true) {
				result = path.poll();
				mark[result.row][result.col] = 5;
				System.out.println("[" + result.row + ", " + result.col + "]");
			}
			// ������ ��θ� 5�� ����Ͽ� ǥ��
			System.out.println("deepFirstSearch ��� ");
			for (int i = 0; i < mark.length; i++) {
				for (int j = 0; j < mark[0].length; j++) {
					System.out.print(mark[i][j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("������ ��ΰ� �����ϴ�.");
		}

	}

}
