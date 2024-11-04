package engine;

import rendering.Renderer;

public class GameLoop implements Runnable {
    
    Thread mainThread;
    Renderer renderer;
    Engine engine;

    public GameLoop(Engine engine,Renderer renderer) {
        this.renderer = renderer;
        this.engine = engine;
    }
    
    
    public void startMainThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }
    
    public void update(float deltaTime){
        engine.step(deltaTime);
    }
    
    public void render(){
        renderer.repaint();
    }
    
    @Override
    public void run() {

        int TARGET_FPS = 60;
        double FRAME_TIME = 1000000000 / TARGET_FPS;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frameCount = 0;
        while (mainThread != null) {

            currentTime = System.nanoTime();
            //System.out.println(currentTime);

            delta += (currentTime - lastTime) / FRAME_TIME;
            timer += currentTime - lastTime;

            lastTime = currentTime;

            if (delta >= 1) {
                update((float)delta);
                render();
                delta--;
                frameCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                timer = 0;
            }
        }
    }
}


