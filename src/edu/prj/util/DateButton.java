package edu.prj.util;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.LineBorder;

public class DateButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1748062069290597554L;

	private DateChooser dateChooser = null;

	private String preLabel = "";

	public DateButton() {
		this(getNowDate());
		setFocusPainted(false);

	}

	public DateButton(SimpleDateFormat df, String dateString) {
		this();
		setText(df, dateString);
	}

	public DateButton(Date date) {
		this("", date);
	}

	public DateButton(String preLabel, Date date) {
		if (preLabel != null)
			this.preLabel = preLabel;
		setDate(date);
		setBorder(null);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dateChooser == null)
					dateChooser = new DateChooser();
				Point p = getLocationOnScreen();
				p.y = p.y + 30;
				dateChooser.showDateChooser(p);
			}
		});
	}

	private static Date getNowDate() {
		return Calendar.getInstance().getTime();
	}

	private static SimpleDateFormat getDefaultDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	// 覆盖父类的方法
	public void setText(String s) {
		Date date;
		try {
			date = getDefaultDateFormat().parse(s);
		} catch (ParseException e) {
			date = getNowDate();
		}
		setDate(date);
	}

	public void setText(SimpleDateFormat df, String s) {
		Date date;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			date = getNowDate();
		}
		setDate(date);
	}

	public void setDate(Date date) {
		super.setText(preLabel + getDefaultDateFormat().format(date));
	}

	public Date getDate() {
		String dateString = getText().substring(preLabel.length());
		try {
			return getDefaultDateFormat().parse(dateString);
		} catch (ParseException e) {
			return getNowDate();
		}

	}

	// 覆盖父类的方法使之无效
	public void addActionListener(ActionListener listener) {
	}

	@SuppressWarnings("unused")
	private class DateChooser extends JPanel implements ActionListener, ChangeListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7919081660325778067L;
		int startYear = 1980; // 默认【最小】显示年份
		int lastYear = 2050; // 默认【最大】显示年份
		int width = 500; // 界面宽度
		int height = 300; // 界面高度

		Color backGroundColor = Color.white; // 底色
		// 日历表格配色----------------//
		Color palletTableColor = Color.gray; // 日历表底色
		Color todayBackColor = Color.red; // 今天背景色
		Color weekFontColor = Color.black; // 星期文字色
		Color dateFontColor = Color.black; // 日期文字色
		Color weekendFontColor = Color.black; // 周末文字色

		// 控制条配色------------------//
		Color controlLineColor = Color.gray; // 控制条底色
		Color controlTextColor = Color.black; // 控制条标签文字色

		Color rbFontColor = Color.white; // RoundBox文字色
		Color rbBorderColor = Color.red; // RoundBox边框色
		Color rbButtonColor = Color.blue; // RoundBox按钮色
		Color rbBtFontColor = Color.red; // RoundBox按钮文字色

		JDialog dialog;
		JSpinner yearSpin;
		JSpinner monthSpin;
		JSpinner hourSpin;
		@SuppressWarnings("rawtypes")
		JComboBox minSpin;
		@SuppressWarnings("rawtypes")
		JComboBox secondBox;
		JButton[][] daysButton = new JButton[6][7];

		DateChooser() {

			setLayout(new BorderLayout());
			setBorder(new LineBorder(backGroundColor, 2));
			setBackground(backGroundColor);

			JPanel topYearAndMonth = createYearAndMonthPanal();
			add(topYearAndMonth, BorderLayout.NORTH);
			JPanel centerWeekAndDay = createWeekAndDayPanal();
			add(centerWeekAndDay, BorderLayout.CENTER);

		}

		@SuppressWarnings("rawtypes")
		private JPanel createYearAndMonthPanal() {

			Calendar c = getCalendar();
			int currentYear = c.get(Calendar.YEAR);
			int currentMonth = c.get(Calendar.MONTH) + 1;
			int currentHour = c.get(Calendar.HOUR_OF_DAY);
			int currentMin = c.get(Calendar.MINUTE);
			int currentSecond = c.get(Calendar.SECOND);

			JPanel result = new JPanel();
			result.setLayout(new FlowLayout());
			result.setBackground(controlLineColor);

			yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear, lastYear, 1));
			yearSpin.setPreferredSize(new Dimension(48, 20));
			yearSpin.setName("Year");
			yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
			yearSpin.addChangeListener(this);
			result.add(yearSpin);

			JLabel yearLabel = new JLabel("年");
			yearLabel.setForeground(controlTextColor);
			result.add(yearLabel);

			monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));
			monthSpin.setPreferredSize(new Dimension(35, 20));
			monthSpin.setName("Month");
			monthSpin.addChangeListener(this);
			result.add(monthSpin);

			JLabel monthLabel = new JLabel("月");
			monthLabel.setForeground(controlTextColor);
			result.add(monthLabel);

			hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1));
			hourSpin.setPreferredSize(new Dimension(35, 20));
			hourSpin.setName("Hour");
			hourSpin.addChangeListener(this);
			result.add(hourSpin);

			JLabel hourLabel = new JLabel("时");
			hourLabel.setForeground(controlTextColor);
			result.add(hourLabel);

			minSpin = new JComboBox();
			addminuteItem(minSpin);
			minSpin.setPreferredSize(new Dimension(45, 20));
			minSpin.setName("Min");
			minSpin.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					JComboBox source = (JComboBox) e.getSource();
					Calendar c = getCalendar();
					minSpin.setSelectedItem("33");
					if (source.getName().equals("Min")) {
						c.set(Calendar.MINUTE, getSelectedMin());
						setDate(c.getTime());
						return;
					}
				}
			});
			result.add(minSpin);

			JLabel minLabel = new JLabel("分");
			hourLabel.setForeground(controlTextColor);
			result.add(minLabel);

			secondBox = new JComboBox();
			addsecondItem(secondBox);
			secondBox.setPreferredSize(new Dimension(55, 20));
			secondBox.setName("Second");
			// secondBox.addActionListener(this) ;
			secondBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					JComboBox source = (JComboBox) e.getSource();
					Calendar c = getCalendar();
					if (source.getName().equals("Second")) {
						c.set(Calendar.SECOND, getSelectedSecond());
						setDate(c.getTime());
						return;
					}
				}
			});

			result.add(secondBox);

			JLabel secondLabel = new JLabel("秒");
			hourLabel.setForeground(controlTextColor);
			result.add(secondLabel);

			return result;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addminuteItem(JComboBox comboBox) {
			for (int i = 0; i < 60; i++) {
				comboBox.addItem(i);
			}
			Calendar c = Calendar.getInstance();
			int minute = c.get(Calendar.MINUTE);
			comboBox.setSelectedItem(minute);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addsecondItem(JComboBox comboBox) {
			for (int i = 0; i < 60; i++) {
				comboBox.addItem(i);
			}
			Calendar c = Calendar.getInstance();
			int minute = c.get(Calendar.SECOND);
			comboBox.setSelectedItem(minute);
		}

		private JPanel createWeekAndDayPanal() {
			String colname[] = { "日", "一", "二", "三", "四", "五", "六" };
			JPanel result = new JPanel();
			// 设置固定字体，以免调用环境改变影响界面美观
			result.setFont(new Font("宋体", Font.PLAIN, 14));
			result.setLayout(new GridLayout(7, 7));
			result.setBackground(Color.white);
			JLabel cell;

			for (int i = 0; i < 7; i++) {
				cell = new JLabel(colname[i]);
				cell.setHorizontalAlignment(JLabel.RIGHT);
				if (i == 0 || i == 6)
					cell.setForeground(weekendFontColor);
				else
					cell.setForeground(weekFontColor);
				result.add(cell);
			}

			int actionCommandId = 0;
			for (int i = 0; i < 6; i++)
				for (int j = 0; j < 7; j++) {
					JButton numberButton = new JButton();
					numberButton.setBorder(null);
					numberButton.setHorizontalAlignment(SwingConstants.RIGHT);
					numberButton.setActionCommand(String.valueOf(actionCommandId));
					numberButton.addActionListener(this);
					numberButton.setBackground(palletTableColor);
					numberButton.setForeground(dateFontColor);
					if (j == 0 || j == 6)
						numberButton.setForeground(weekendFontColor);
					else
						numberButton.setForeground(dateFontColor);
					daysButton[i][j] = numberButton;
					result.add(numberButton);
					actionCommandId++;
				}

			return result;
		}

		private JDialog createDialog(Frame owner) {
			JDialog result = new JDialog(owner, "日期时间选择", true);
			result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			result.getContentPane().add(this, BorderLayout.CENTER);
			result.pack();
			result.setSize(width, height);
			return result;
		}

		@SuppressWarnings("deprecation")
		void showDateChooser(Point position) {
			Frame owner = (Frame) SwingUtilities.getWindowAncestor(DateButton.this);
			if (dialog == null || dialog.getOwner() != owner)
				dialog = createDialog(owner);
			dialog.setLocation(getAppropriateLocation(owner, position));
			flushWeekAndDay();
			dialog.show();
		}

		Point getAppropriateLocation(Frame owner, Point position) {
			Point result = new Point(position);
			Point p = owner.getLocation();
			int offsetX = (position.x + width) - (p.x + owner.getWidth());
			int offsetY = (position.y + height) - (p.y + owner.getHeight());

			if (offsetX > 0) {
				result.x = offsetX;
			}

			if (offsetY > 0) {
				result.y = offsetY;
			}

			return result;

		}

		private Calendar getCalendar() {
			Calendar result = Calendar.getInstance();
			result.setTime(getDate());
			return result;
		}

		private int getSelectedYear() {
			return ((Integer) yearSpin.getValue()).intValue();
		}

		private int getSelectedMonth() {
			return ((Integer) monthSpin.getValue()).intValue();
		}

		private int getSelectedHour() {
			return ((Integer) hourSpin.getValue()).intValue();
		}

		private int getSelectedMin() {
			return (Integer) this.minSpin.getSelectedItem();
		}

		private int getSelectedSecond() {
			return (Integer) this.secondBox.getSelectedItem();
		}

		private void dayColorUpdate(boolean isOldDay) {
			Calendar c = getCalendar();
			int day = c.get(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, 1);
			int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
			int i = actionCommandId / 7;
			int j = actionCommandId % 7;
			if (isOldDay)
				daysButton[i][j].setForeground(dateFontColor);
			else
				daysButton[i][j].setForeground(todayBackColor);
		}

		private void flushWeekAndDay() {
			Calendar c = getCalendar();
			c.set(Calendar.DAY_OF_MONTH, 1);
			int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					String s = "";
					if (dayNo >= 1 && dayNo <= maxDayNo)
						s = String.valueOf(dayNo);
					daysButton[i][j].setText(s);
					dayNo++;
				}
			}
			dayColorUpdate(false);
		}

		public void stateChanged(ChangeEvent e) {
			JSpinner source = (JSpinner) e.getSource();
			Calendar c = getCalendar();
			if (source.getName().equals("Hour")) {
				c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
				setDate(c.getTime());
				return;
			}

			dayColorUpdate(true);

			if (source.getName().equals("Year"))
				c.set(Calendar.YEAR, getSelectedYear());
			else
				// (source.getName().equals("Month"))
				c.set(Calendar.MONTH, getSelectedMonth() - 1);
			setDate(c.getTime());
			flushWeekAndDay();
		}

		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if (source.getText().length() == 0)
				return;
			dayColorUpdate(true);
			source.setForeground(todayBackColor);
			int newDay = Integer.parseInt(source.getText());
			Calendar c = getCalendar();
			c.set(Calendar.DAY_OF_MONTH, newDay);
			setDate(c.getTime());
		}

	}
}