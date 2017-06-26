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

		int mazeRowSize = 10;	//미로의 크기
		int mazeColSize = 10;

		//8방향 오프셋
		move[0] = new Offset(0, -1);	
		move[1] = new Offset(1, -1);
		move[2] = new Offset(1, 0);
		move[3] = new Offset(1, 1);
		move[4] = new Offset(0, 1);
		move[5] = new Offset(-1, 1);
		move[6] = new Offset(-1, 0);
		move[7] = new Offset(-1, -1);

		int[][] maze = new int[mazeRowSize][mazeColSize];	//랜덤한 미로 생성하기 위한 2차원 배열
		int[][] mark = new int[mazeRowSize][mazeColSize];	//탐색한 길을 마크하기 위한 2차원 배열
		dest.row = mazeRowSize - 2;	//도착 위치 좌표 설정
		dest.col = mazeColSize - 2;

		//랜덤한 미로 생성
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
					maze[i][j] = 1;
				} else {
					maze[i][j] = (int) (Math.random() * 2);	//0은 갈수 있는 길, 1은 벽
				}
			}
		}
		maze[maze.length - 2][maze[0].length - 2] = 0;	//마지막 출구는 0으로 갈수있는길로 설정
		maze[1][1] = 0;
		// 생성한 미로 출력
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}

		// ---------------------------------- 초기설정 완료

		nowPos.col = start.col;
		nowPos.row = start.row; // 시작값을 현재 위치로 저장

		while (nowPos.col != dest.col || nowPos.row != dest.row) {	//출구까지 도달할때가지 반복
			
			nextPos = deepFirstSearch(maze, nowPos);	//다음 경로 찾기
			//시작위치에서 더이상 갈곳이 없으면 가능한 경로가 없음. 
			if (nextPos == -1 && nowPos.row == start.row && nowPos.col == start.col) {
				wayOut = false;
				break;
			}
			//갈길이 없으면 현재위치를 벽으로 설정하고 이전위치로 돌아와서 다른경로 검색
			if (nextPos == -1) {
				maze[nowPos.row][nowPos.col] = 1;
				nowPos = path.removeLast();
				wayOut = false;
			}

			// 다음 경로 있음
			else {
				nowPos.col += move[nextPos].col;
				nowPos.row += move[nextPos].row;
			}
			wayOut = true;

		}

		if (wayOut == true) {
			// 마지막 출구값 푸쉬
			path.add(nowPos);

			// 가능한 경로 좌표 출력
			System.out.println("deepFirstSearch 경로 좌표 ");
			while (path.isEmpty() != true) {
				result = path.poll();
				mark[result.row][result.col] = 5;
				System.out.println("[" + result.row + ", " + result.col + "]");
			}
			// 가능한 경로를 5로 출력하여 표시
			System.out.println("deepFirstSearch 경로 ");
			for (int i = 0; i < mark.length; i++) {
				for (int j = 0; j < mark[0].length; j++) {
					System.out.print(mark[i][j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("가능한 경로가 없습니다.");
		}

	}

}
