package wang.xiunian.surfaceview

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import wang.xiunian.scaffold.log.L

class MainActivity : AppCompatActivity() {
    private lateinit var mGLSurfaceView: GLSurfaceView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGLSurfaceView = findViewById(R.id.surfaceview)
        mGLSurfaceView.setEGLContextClientVersion(2)
        L.d("MainActivity","test for main")
    }
}