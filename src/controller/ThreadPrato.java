package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread{

	private int idPrato;
	private Semaphore semaforo;
	
	public ThreadPrato(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		pratoCozindo();
//		------------------Start---------------
		try {
			semaforo.acquire();
			pratoEntrega();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaforo.release();
		}
//		-------------------End-----------------
		
	}

	private void pratoCozindo() {
		int tempoTotal = 0;
		if(idPrato % 2 == 1) {
			tempoTotal = (int)((Math.random() * 301) + 500);
		}else {
			tempoTotal = (int)((Math.random() * 601) + 600);
		}
		int tempoCozido = 0;
		int cozimento = 100;
		int tempo = 100;
		System.out.println("#"+idPrato+" começou a ser feito");
		while(tempoCozido < tempoTotal) {
			tempoCozido += cozimento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(idPrato % 2 == 1) {
					System.out.println("Sopa de Cebola #"+idPrato+" já assou "+ (tempoCozido*100)/tempoTotal +"%");
			}else {
				System.out.println("Lasanha a Bolonhesa #"+idPrato+" já assou "+ (tempoCozido*100)/tempoTotal +"%");
			}
		}
		System.out.println("#"+idPrato+" está pronto");
	}

	private void pratoEntrega() {
		System.out.println("#"+idPrato+" está sendo entregue");
		int tempo = 500;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(idPrato % 2 == 1) {
			System.out.println("Sopa de Cebola #"+idPrato+" foi entregue");
		}else {
			System.out.println("Lasanha a Bolonhesa #"+idPrato+" foi entregue");
		}
	}
}