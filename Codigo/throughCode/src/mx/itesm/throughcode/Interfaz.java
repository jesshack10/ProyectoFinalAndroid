package mx.itesm.throughcode;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class Interfaz extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interfaz);
		
	    findViewById(R.id.frenteButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.atrasButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.derechaButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.izquierdaButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.ledButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.rgbButton).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.buzzerButton).setOnTouchListener(new MyTouchListener());
	    
	    findViewById(R.id.tableRow1).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow2).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow3).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow4).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow5).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow6).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow7).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow8).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow9).setOnDragListener(new MyDragListener());
	    findViewById(R.id.tableRow10).setOnDragListener(new MyDragListener());
	    
	}
	
	  private final class MyTouchListener implements OnTouchListener {
		    public boolean onTouch(View view, MotionEvent motionEvent) {
		      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		        ClipData data = ClipData.newPlainText("", "");
		        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		        view.startDrag(data, shadowBuilder, view, 0);
		        view.setVisibility(View.INVISIBLE);
		        return true;
		      } else {
		        return false;
		      }
		    }
		  }
	  
	  class MyDragListener implements OnDragListener {
		    Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		    Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		    @Override
		    public boolean onDrag(View v, DragEvent event) {
		      int action = event.getAction();
		      switch (event.getAction()) {
		      case DragEvent.ACTION_DRAG_STARTED:
		        // do nothing
		        break;
		      case DragEvent.ACTION_DRAG_ENTERED:
		        v.setBackgroundDrawable(enterShape);
		        break;
		      case DragEvent.ACTION_DRAG_EXITED:
		        v.setBackgroundDrawable(normalShape);
		        break;
		      case DragEvent.ACTION_DROP:
		        // Dropped, reassign View to ViewGroup
		        View view = (View) event.getLocalState();
		        ViewGroup owner = (ViewGroup) view.getParent();
		        owner.removeView(view);
		        LinearLayout container = (LinearLayout) v;
		        container.addView(view);
		        view.setVisibility(View.VISIBLE);
		        break;
		      case DragEvent.ACTION_DRAG_ENDED:
		        v.setBackgroundDrawable(normalShape);
		      default:
		        break;
		      }
		      return true;
		    }
		  }
}
