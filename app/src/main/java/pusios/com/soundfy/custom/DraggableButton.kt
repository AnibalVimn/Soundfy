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
import android.view.ViewConfiguration

class DraggableButton: FrameLayout {

    private lateinit var button: Button
    private var slop = 0
    private var lastX = 0f
    private var downX = 0f
    private var moving = false

    constructor(context: Context) : this(context, null) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        slop = ViewConfiguration.get(context).scaledTouchSlop
    }

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
            downX = event.x
            return false
        }
        if (lastX == 0f) {
            return false
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            tryToTranslateButton(event)
        } else if ((event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL)
                && button.translationX != 0f) {
            reset()
            return true
        }
        return false
    }

    private fun isUserSwiping(event: MotionEvent): Boolean {
        return (Math.abs(event.x - downX) > slop || moving)
    }

    private fun isDistanceWithinLimits(distance: Float): Boolean {
        val maxDistance = (width - button.width) / 2
        return Math.abs(button.translationX + distance) in 0..maxDistance
    }

    private fun tryToTranslateButton(event: MotionEvent) {
        val distance = event.x - lastX
        if (isUserSwiping(event) && isDistanceWithinLimits(distance)) {
            translateButton(distance, event.x)
        }
    }

    private fun translateButton(distance: Float, x: Float) {
        moving = true
        button.translationX += distance
        lastX = x
    }

    private fun reset() {
        button.animate().translationX(0f)
        lastX = 0f
        moving = false
    }

    private fun hitButton(event: MotionEvent): Boolean {
        val hitRect = Rect()
        button.getHitRect(hitRect)
        hitRect.left += (button.parent as View).left
        hitRect.right += (button.parent as View).left
        return hitRect.contains(event.x.toInt(), event.y.toInt())
    }
}