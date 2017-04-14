package pusios.com.soundfy.custom

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout

class DraggableButton: FrameLayout {

    private lateinit var button: Button

    private var lastX = 0f

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child is LinearLayout) {
            button = child.getChildAt(0) as Button
        }
        super.addView(child, params)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val result = onTouchEvent(ev)
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && hitButton(event)) {
            lastX = event.x
            return false
        }

        if (lastX == 0f) {
            return false
        }

        when(event.action) {
            MotionEvent.ACTION_MOVE -> {
                button.translationX += event.x - lastX
                lastX = event.x
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (button.translationX != 0f) {
                    button.animate().translationX(0f)
                    lastX = 0f
                    return true
                }
            }
        }

        return false
    }

    private fun hitButton(event: MotionEvent): Boolean {
        val hitRect = Rect()
        button.getHitRect(hitRect)
        hitRect.left += (button.parent as View).left
        hitRect.right += (button.parent as View).left
        return hitRect.contains(event.x.toInt(), event.y.toInt())
    }
}